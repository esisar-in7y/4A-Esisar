#!/bin/bash

NPOP=$1
MACHINE_TYPE=$2

#	Numéro de zone
Arr=(0 32 64 96 32)
AREA=${Arr[$NPOP]}

echo "conf terminal"
echo "routeur ospf"

#	Routeur Backbone
if [[ $MACHINE_TYPE -eq 0 ]]; then 
	echo "network 10.0.0.x/30 area 0"
	echo "area $AREA virtual_link 10.224.$AREA.25y"
	# Exigeance 4
	echo "# area $AREA range 10.32.0.0/11"
#	Router intra
elif [[ $MACHINE_TYPE -eq 1 ]]; then
	echo "network 10.$AREA.0.0/11 area $AREA"
#	Routeur HQ
elif [[ $MACHINE_TYPE -eq 2 ]]; then
	echo "network 10.$AREA.0.0/20 area $AREA"
	# Exigeance 3
	echo "# redistribute static"
fi


echo "network 10.224.$AREA.0/24 area $AREA"
echo "exit"

#	Routeur Backbone
if [[ $MACHINE_TYPE -eq 0 ]]; then 
	echo "# interface eth1"
	# Exigeance 5
	echo "# ip ospf cost 1000"
	echo "# exit"
#	Routeur HQ
elif [[ $MACHINE_TYPE -eq 2 ]]; then
	# Exigeance 3
	echo "# ip route a.b.c.0/24 Null0"
fi

echo "exit"
echo "show run"
