#!/bin/bash

NPOP=$1
MACHINE_TYPE=$2

# fin du local area 0
IPA=$3
# fin du local area POP
IPB=$4
# Numéro de zone
Arr=(0 32 64 96 32)
AREA=${Arr[$NPOP]}

echo "vtysh"
echo "conf terminal"
echo "router ospf"

# Routeur Backbone
if [[ $MACHINE_TYPE -eq 0 ]]; then 
 echo "network 10.0.0.$IPA/30 area 0"
 echo "area $AREA virtual-link 10.254.$AREA.$IPB"
 # Exigeance 4
 echo "# area $AREA range 10.32.0.0/11"
# Router non Backbone
elif [[ $MACHINE_TYPE -eq 1 ]]; then
 echo "network 10.$AREA.0.0/11 area $AREA"
# Routeur HQ
elif [[ $MACHINE_TYPE -eq 2 ]]; then
 echo "network 10.$AREA.0.0/20 area $AREA"
 # Exigeance 3
 echo "redistribute static"
fi


echo "network 10.224.$AREA.0/24 area $AREA"
echo "exit"

# Routeur Backbone
if [[ $MACHINE_TYPE -eq 0 ]]; then 
 echo "# interface eth1"
 # Exigeance 5
 echo "# ip ospf cost 1000"
 echo "# exit"
# Routeur HQ
elif [[ $MACHINE_TYPE -eq 2 ]]; then
 # Exigeance 3
 echo "# ip route a.b.c.0/24 Null0"
fi

echo "exit"
echo "write"
echo "show run"
