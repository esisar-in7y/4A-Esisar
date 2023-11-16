PARAMS= "-Wno-unused-parameter -Wno-unused-but-set-variable"

eval $(opam env)
find . -type f -name "*.sh" -delete
find . -type f -name "*.txt" -delete
find . -type f -name "*.o" -delete

for file in *.c
do
    sh -c "gcc $PARAMS $file -o $file.gcc.o 2>&1 | tee $file.gcc.sh"
    sh -c "gcc $PARAMS -Wall -Wextra -pedantic $file -o $file.gcc_opts.o 2>&1 | tee $file.gcc_opts.sh"
    sh -c "gcc $PARAMS -fsanitize=address -static-libasan $file -o $file.gcc_asan.o 2>&1 | tee $file.gcc_asan.sh"
    sh -c "valgrind ./$file.gcc_opts.o 2>&1 | tee $file.valgrind.sh" #--log-file=\"$file.valgrind.txt\""
    sh -c "frama-c -eva -rte $file 2>&1 | tee $file.frama_c.sh"
done
find . -type f -name "*.sh" -empty -delete
find . -type f -name "*.o" -delete