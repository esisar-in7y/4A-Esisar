==115229== Memcheck, a memory error detector
==115229== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==115229== Using Valgrind-3.16.1 and LibVEX; rerun with -h for copyright info
==115229== Command: ./sampleF.c.gcc_opts.o
==115229== 
==115229== Conditional jump or move depends on uninitialised value(s)
==115229==    at 0x109163: main (in /home/userir/Downloads/TP_2023-24/_code/sampleF.c.gcc_opts.o)
==115229== 
==115229== 
==115229== HEAP SUMMARY:
==115229==     in use at exit: 4 bytes in 1 blocks
==115229==   total heap usage: 1 allocs, 0 frees, 4 bytes allocated
==115229== 
==115229== LEAK SUMMARY:
==115229==    definitely lost: 4 bytes in 1 blocks
==115229==    indirectly lost: 0 bytes in 0 blocks
==115229==      possibly lost: 0 bytes in 0 blocks
==115229==    still reachable: 0 bytes in 0 blocks
==115229==         suppressed: 0 bytes in 0 blocks
==115229== Rerun with --leak-check=full to see details of leaked memory
==115229== 
==115229== Use --track-origins=yes to see where uninitialised values come from
==115229== For lists of detected and suppressed errors, rerun with: -s
==115229== ERROR SUMMARY: 1 errors from 1 contexts (suppressed: 0 from 0)
