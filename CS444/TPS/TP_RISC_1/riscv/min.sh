 riscv64-unknown-elf-as -march=rv64g minmax.s -o main.o
 riscv64-unknown-elf-objdump -d main.o
 riscv64-unknown-elf-gcc -march=rv64g main.o -o main
 spike -d pk ./main
