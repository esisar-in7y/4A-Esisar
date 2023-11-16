==115343== Memcheck, a memory error detector
==115343== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==115343== Using Valgrind-3.16.1 and LibVEX; rerun with -h for copyright info
==115343== Command: ./sampleI.c.gcc_opts.o
==115343== 
==115343== 
==115343== HEAP SUMMARY:
==115343==     in use at exit: 4 bytes in 1 blocks
==115343==   total heap usage: 2 allocs, 1 frees, 8 bytes allocated
==115343== 
==115343== LEAK SUMMARY:
==115343==    definitely lost: 4 bytes in 1 blocks
==115343==    indirectly lost: 0 bytes in 0 blocks
==115343==      possibly lost: 0 bytes in 0 blocks
==115343==    still reachable: 0 bytes in 0 blocks
==115343==         suppressed: 0 bytes in 0 blocks
==115343== Rerun with --leak-check=full to see details of leaked memory
==115343== 
==115343== For lists of detected and suppressed errors, rerun with: -s
==115343== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 0 from 0)
