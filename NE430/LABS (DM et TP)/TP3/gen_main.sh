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
#	Router HQ
elif [[ $MACHINE_TYPE -eq 1 ]]; then
	echo "network 10.$AREA.0.0/11 area $AREA"
#	Routeur pdv
elif [[ $MACHINE_TYPE -eq 2 ]]; then
	echo "network 10.$AREA.0.0/20 area $AREA"
fi


echo "network 10.224.$AREA.0/24 area $AREA"
echo "exit"
echo "exit"
echo "show run"
