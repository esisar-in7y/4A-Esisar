==115386== Memcheck, a memory error detector
==115386== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==115386== Using Valgrind-3.16.1 and LibVEX; rerun with -h for copyright info
==115386== Command: ./sampleJ.c.gcc_opts.o
==115386== 
==115386== Invalid write of size 4
==115386==    at 0x10916A: main (in /home/userir/Downloads/TP_2023-24/_code/sampleJ.c.gcc_opts.o)
==115386==  Address 0x4a3303c is 4 bytes before a block of size 12 alloc'd
==115386==    at 0x483877F: malloc (vg_replace_malloc.c:307)
==115386==    by 0x109156: main (in /home/userir/Downloads/TP_2023-24/_code/sampleJ.c.gcc_opts.o)
==115386== 
==115386== 
==115386== HEAP SUMMARY:
==115386==     in use at exit: 0 bytes in 0 blocks
==115386==   total heap usage: 1 allocs, 1 frees, 12 bytes allocated
==115386== 
==115386== All heap blocks were freed -- no leaks are possible
==115386== 
==115386== For lists of detected and suppressed errors, rerun with: -s
==115386== ERROR SUMMARY: 1 errors from 1 contexts (suppressed: 0 from 0)
