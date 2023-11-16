==114981== Memcheck, a memory error detector
==114981== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==114981== Using Valgrind-3.16.1 and LibVEX; rerun with -h for copyright info
==114981== Command: ./demoadsan.c.gcc_opts.o
==114981== 
string is: Hello world!
==114981== 
==114981== HEAP SUMMARY:
==114981==     in use at exit: 100 bytes in 1 blocks
==114981==   total heap usage: 2 allocs, 1 frees, 4,196 bytes allocated
==114981== 
==114981== LEAK SUMMARY:
==114981==    definitely lost: 100 bytes in 1 blocks
==114981==    indirectly lost: 0 bytes in 0 blocks
==114981==      possibly lost: 0 bytes in 0 blocks
==114981==    still reachable: 0 bytes in 0 blocks
==114981==         suppressed: 0 bytes in 0 blocks
==114981== Rerun with --leak-check=full to see details of leaked memory
==114981== 
==114981== For lists of detected and suppressed errors, rerun with: -s
==114981== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 0 from 0)
