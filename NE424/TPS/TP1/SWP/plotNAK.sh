#!/bin/bash

longueur=50000
sed -i '/plot.*/cplot "logOG" w lp title "Without NAK", "logNAK" w lp title "With NAK"' graph.plt

echo "# Without NAK"
rm swp.c
cp swpOG.c swp.c
make clean >> /dev/null
make >> /dev/null
echo "" > logOG
for tdp in 0 2 4 6 8 10  12 15 20; do
	echo -n "$tdp " >> "logOG"
	./swp $longueur $tdp 0 7 0 >> /dev/null
	echo "$?" >> "logOG"
done

echo "# With NAK"
rm swp.c
cp swpNAK.c swp.c
make clean >> /dev/null
make >> /dev/null
echo "" > logNAK
for tdp in 0 2 4 6 8 10  12 15 20; do
	echo -n "$tdp " >> "logNAK"
	./swp $longueur $tdp 0 7 0 >> /dev/null
	echo "$?" >> "logNAK"
done


gnuplot graph.plt

