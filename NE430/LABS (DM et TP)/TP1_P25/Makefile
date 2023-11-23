PARAMS=-g
SHELL := /bin/bash

validation:
	@reset
	make clean
	make main
	echo "insert route took:"
	time (echo '' | ./main routes)
	echo "insert route took:"
	time (cat iptest | ./main routes > output)
	diff -q output iptest.resultat

test:
	@reset
	make clean
	make main
	cat iptest | ./main routes

run:
	@reset
	make clean
	make main
	./main r

main: main.o MyAlgo.o
	gcc $(PARAMS) -o main main.o MyAlgo.o

main.o: main.c
	gcc $(PARAMS) -c main.c

MyAlgo.o: MyAlgo.c
	gcc $(PARAMS) -c MyAlgo.c
	
clean:
	rm -rf *.o main
