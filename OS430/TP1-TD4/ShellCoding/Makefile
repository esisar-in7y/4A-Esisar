

all: assemble link run

assemble:
	nasm -f elf64 -l main.lst  main.asm

link:
	gcc -o main  main.o

run:
	./main
