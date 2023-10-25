#!/bin/bash

log=$1
longueur=50000
echo "|RWS\tdp|0|2|4|6|8|10|12|15|20|" > $log
echo "|--|--|--|--|--|--|--|--|--|--|" > $log

for RWS in 1 2 3 4; do
	
	echo -n "|$RWS|" >> $log
	sed "s/RWS [0-9]/RWS $RWS/" -i swp.c
	make

	for tdp in 0 2 4 6 8 10  12 15 20; do
		./swp $longueur $tdp 0 7 0 >> /dev/null
		echo -n "$?|" >> $log
	done
	echo "" >> $log
done
