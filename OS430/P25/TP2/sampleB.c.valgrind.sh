==115090== Memcheck, a memory error detector
==115090== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==115090== Using Valgrind-3.16.1 and LibVEX; rerun with -h for copyright info
==115090== Command: ./sampleB.c.gcc_opts.o
==115090== 
==115090== Invalid write of size 4
==115090==    at 0x10916A: main (in /home/userir/Downloads/TP_2023-24/_code/sampleB.c.gcc_opts.o)
==115090==  Address 0x4a3304c is 0 bytes after a block of size 12 alloc'd
==115090==    at 0x483877F: malloc (vg_replace_malloc.c:307)
==115090==    by 0x109156: main (in /home/userir/Downloads/TP_2023-24/_code/sampleB.c.gcc_opts.o)
==115090== 
==115090== 
==115090== HEAP SUMMARY:
==115090==     in use at exit: 0 bytes in 0 blocks
==115090==   total heap usage: 1 allocs, 1 frees, 12 bytes allocated
==115090== 
==115090== All heap blocks were freed -- no leaks are possible
==115090== 
==115090== For lists of detected and suppressed errors, rerun with: -s
==115090== ERROR SUMMARY: 1 errors from 1 contexts (suppressed: 0 from 0)
