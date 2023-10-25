riscv64-unknown-elf-as -march=rv64g str_codecesar.s -o str_codecesar.o
riscv64-unknown-elf-as -march=rv64g libprint.s -o libprint.o
riscv64-unknown-elf-objdump -d str_codecesar.o 
riscv64-unknown-elf-gcc str_codecesar.o libprint.o -o codecesar &&
riscv64-unknown-elf-nm codecesar | grep -E 'main|Caesar' &&
spike pk ./codecesar
