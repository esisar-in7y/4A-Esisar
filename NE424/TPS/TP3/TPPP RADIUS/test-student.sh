#!/bin/bash
# virtual student test tool to check server is ok
# to be run on Deb-CD-radius-2018 image
# (needs tptest and ssh-key)

test $(id -u) -eq 0 || { echo "Must be root"; exit 2; }
test $# -eq 2 || { echo "Usage: test-student.sh acname ipaddress"; exit 1; }

AC=$1
IP=$2

LOG=/dev/stdout
OPT=/etc/ppp/pppoe-server-options
RADIUS_DEB=libfreeradius-client2_1.1.6-7_amd64.deb

cancel () {
    killall pppoe-server
}

setup () {
    echo Installing ssh key
    # TODO check this
    #cp id.rsa.pub /root/.ssh

    apt-get update
    apt-get install -y pppoe

    if dpkg -s libfreeradius-client2; then
	echo "libfreeradius-client2 already installed";
    else
	if ! test -f $RADIUS_DEB; then
	    echo "Download libfreeradius-client2"
	    wget http://192.168.130.202/~deleuzec/NE424-Radius/$RADIUS_DEB
	fi
	echo "Installing libfreeradius-client2"
	dpkg -i $RADIUS_DEB
    fi
}

trap cancel INT
result=0

dotest () {
    echo "*** Running test $1 $2" >> $LOG
    pppoe-server -C $AC -L 10.0.0.42
    sleep 1
    echo "..." >> $LOG
    tptest $1 $AC $2 | tee .log >> $LOG
    if grep -q Success .log; then echo "OK..." >> $LOG
    else
	echo "Koooooooooooooo " >> $LOG
	result=1
    fi
    killall pppoe-server
}


setup

apt-get install -y pppoe
touch $OPT

echo -e "tpne424 * motdepasse *\nserver * chef" > /etc/ppp/pap-secrets
echo -e "tpne424 * motdepasse *\nserver * chef" > /etc/ppp/chap-secrets

echo "require-pap" > $OPT
dotest 1

echo "require-eap" > $OPT
dotest 2

cat > $OPT <<EOF
user server
refuse-pap
refuse-chap
refuse-mschap
refuse-mschap-v2
EOF
dotest 3

# setup to use 210 radius server
sed -i '/^authserver/s/.*/authserver 192.168.130.210/' /etc/radiusclient/radiusclient.conf
echo "192.168.130.210 toto" > /etc/radiusclient/servers
echo -e "require-pap\nplugin radius.so" > $OPT
touch /etc/radiusclient/port-id-map
dotest 4
dotest 5

# setup to use local radius server
apt-get install -y freeradius
sed -i '/^authserver/s/.*/authserver 127.0.0.1/' /etc/radiusclient/radiusclient.conf
echo "127.0.0.1 testing123" > /etc/radiusclient/servers
cat > /etc/freeradius/3.0/users <<EOF
tpne424 Cleartext-Password :="motdepasse"
        Service-Type = Framed-User,
	Framed-Protocol = PPP
EOF
sed -i '/auth = no/s/.*/     auth = yes/' /etc/freeradius/3.0/radiusd.conf
/etc/init.d/freeradius restart
dotest 6 $IP

# setup delegation for prof.com realm
cat > /etc/freeradius/3.0/proxy.conf <<EOF
home_server serveur_prof {
    type = auth
    ipaddr = 192.168.130.210
    port = 1812
    secret = toto
}

home_server_pool prof_pool {
    type = fail-over
    home_server = serveur_prof
}

realm prof.com {
    auth_pool = prof_pool
}
EOF
/etc/init.d/freeradius restart
dotest 7
dotest 8

# results
if test $result -eq 0; then echo ok; else echo "FAIIIIILLL"; fi
exit $result
