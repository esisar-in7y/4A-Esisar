==115419== Memcheck, a memory error detector
==115419== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==115419== Using Valgrind-3.16.1 and LibVEX; rerun with -h for copyright info
==115419== Command: ./sampleK.c.gcc_opts.o
==115419== 
==115419== Conditional jump or move depends on uninitialised value(s)
==115419==    at 0x109135: myMax (in /home/userir/Downloads/TP_2023-24/_code/sampleK.c.gcc_opts.o)
==115419==    by 0x10915E: myAbs (in /home/userir/Downloads/TP_2023-24/_code/sampleK.c.gcc_opts.o)
==115419==    by 0x109179: foo (in /home/userir/Downloads/TP_2023-24/_code/sampleK.c.gcc_opts.o)
==115419==    by 0x109196: main (in /home/userir/Downloads/TP_2023-24/_code/sampleK.c.gcc_opts.o)
==115419== 
==115419== 
==115419== HEAP SUMMARY:
==115419==     in use at exit: 0 bytes in 0 blocks
==115419==   total heap usage: 0 allocs, 0 frees, 0 bytes allocated
==115419== 
==115419== All heap blocks were freed -- no leaks are possible
==115419== 
==115419== Use --track-origins=yes to see where uninitialised values come from
==115419== For lists of detected and suppressed errors, rerun with: -s
==115419== ERROR SUMMARY: 1 errors from 1 contexts (suppressed: 0 from 0)
