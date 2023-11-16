==115131== Memcheck, a memory error detector
==115131== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==115131== Using Valgrind-3.16.1 and LibVEX; rerun with -h for copyright info
==115131== Command: ./sampleC.c.gcc_opts.o
==115131== 
==115131== Conditional jump or move depends on uninitialised value(s)
==115131==    at 0x109141: main (in /home/userir/Downloads/TP_2023-24/_code/sampleC.c.gcc_opts.o)
==115131== 
==115131== 
==115131== HEAP SUMMARY:
==115131==     in use at exit: 0 bytes in 0 blocks
==115131==   total heap usage: 0 allocs, 0 frees, 0 bytes allocated
==115131== 
==115131== All heap blocks were freed -- no leaks are possible
==115131== 
==115131== Use --track-origins=yes to see where uninitialised values come from
==115131== For lists of detected and suppressed errors, rerun with: -s
==115131== ERROR SUMMARY: 1 errors from 1 contexts (suppressed: 0 from 0)
