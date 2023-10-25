riscv64-unknown-elf-as -march=rv64g len.s -o len.o
riscv64-unknown-elf-as -march=rv64g libprint.s -o libprint.o
riscv64-unknown-elf-objdump -d len.o 
riscv64-unknown-elf-gcc len.o libprint.o -o len &&
riscv64-unknown-elf-nm len | grep main &&
spike pk ./len
