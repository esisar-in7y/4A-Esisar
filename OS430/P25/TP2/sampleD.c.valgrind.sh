==115164== Memcheck, a memory error detector
==115164== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==115164== Using Valgrind-3.16.1 and LibVEX; rerun with -h for copyright info
==115164== Command: ./sampleD.c.gcc_opts.o
==115164== 
==115164== Invalid write of size 4
==115164==    at 0x109135: main (in /home/userir/Downloads/TP_2023-24/_code/sampleD.c.gcc_opts.o)
==115164==  Address 0x0 is not stack'd, malloc'd or (recently) free'd
==115164== 
==115164== 
==115164== Process terminating with default action of signal 11 (SIGSEGV)
==115164==  Access not within mapped region at address 0x0
==115164==    at 0x109135: main (in /home/userir/Downloads/TP_2023-24/_code/sampleD.c.gcc_opts.o)
==115164==  If you believe this happened as a result of a stack
==115164==  overflow in your program's main thread (unlikely but
==115164==  possible), you can try to increase the size of the
==115164==  main thread stack using the --main-stacksize= flag.
==115164==  The main thread stack size used in this run was 16777216.
==115164== 
==115164== HEAP SUMMARY:
==115164==     in use at exit: 0 bytes in 0 blocks
==115164==   total heap usage: 0 allocs, 0 frees, 0 bytes allocated
==115164== 
==115164== All heap blocks were freed -- no leaks are possible
==115164== 
==115164== For lists of detected and suppressed errors, rerun with: -s
==115164== ERROR SUMMARY: 1 errors from 1 contexts (suppressed: 0 from 0)
