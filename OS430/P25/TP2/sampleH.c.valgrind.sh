==115310== Memcheck, a memory error detector
==115310== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==115310== Using Valgrind-3.16.1 and LibVEX; rerun with -h for copyright info
==115310== Command: ./sampleH.c.gcc_opts.o
==115310== 
==115310== Invalid read of size 4
==115310==    at 0x109135: main (in /home/userir/Downloads/TP_2023-24/_code/sampleH.c.gcc_opts.o)
==115310==  Address 0x0 is not stack'd, malloc'd or (recently) free'd
==115310== 
==115310== 
==115310== Process terminating with default action of signal 11 (SIGSEGV)
==115310==  Access not within mapped region at address 0x0
==115310==    at 0x109135: main (in /home/userir/Downloads/TP_2023-24/_code/sampleH.c.gcc_opts.o)
==115310==  If you believe this happened as a result of a stack
==115310==  overflow in your program's main thread (unlikely but
==115310==  possible), you can try to increase the size of the
==115310==  main thread stack using the --main-stacksize= flag.
==115310==  The main thread stack size used in this run was 16777216.
==115310== 
==115310== HEAP SUMMARY:
==115310==     in use at exit: 0 bytes in 0 blocks
==115310==   total heap usage: 0 allocs, 0 frees, 0 bytes allocated
==115310== 
==115310== All heap blocks were freed -- no leaks are possible
==115310== 
==115310== For lists of detected and suppressed errors, rerun with: -s
==115310== ERROR SUMMARY: 1 errors from 1 contexts (suppressed: 0 from 0)
