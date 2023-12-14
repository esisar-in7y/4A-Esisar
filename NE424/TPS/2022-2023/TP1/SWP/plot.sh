#!/bin/bash

longueur=50000
sed -i '/plot.*/cplot "log1" w lp title "RWS1", "log2" w lp title "RWS2", "log3" w lp title "RWS3", "log4" w lp title "RWS4"' graph.plt

for RWS in 1 2 3 4; do

	echo "# RWS=$RWS"
	sed "s/RWS [0-9]/RWS $RWS/" -i swp.c
	make >> /dev/null

	for tdp in 0 2 4 6 8 10  12 15 20; do
		echo -n "$tdp " >> "log$RWS"
		./swp $longueur $tdp 0 7 0 >> /dev/null
		echo "$?" >> "log$RWS"
	done
done

gnuplot graph.plt

