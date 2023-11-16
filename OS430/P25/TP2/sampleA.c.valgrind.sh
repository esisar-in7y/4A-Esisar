==115057== Memcheck, a memory error detector
==115057== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==115057== Using Valgrind-3.16.1 and LibVEX; rerun with -h for copyright info
==115057== Command: ./sampleA.c.gcc_opts.o
==115057== 
==115057== Invalid write of size 4
==115057==    at 0x109172: main (in /home/userir/Downloads/TP_2023-24/_code/sampleA.c.gcc_opts.o)
==115057==  Address 0x4a33040 is 0 bytes inside a block of size 4 free'd
==115057==    at 0x48399AB: free (vg_replace_malloc.c:538)
==115057==    by 0x10916D: main (in /home/userir/Downloads/TP_2023-24/_code/sampleA.c.gcc_opts.o)
==115057==  Block was alloc'd at
==115057==    at 0x483877F: malloc (vg_replace_malloc.c:307)
==115057==    by 0x109156: main (in /home/userir/Downloads/TP_2023-24/_code/sampleA.c.gcc_opts.o)
==115057== 
==115057== 
==115057== HEAP SUMMARY:
==115057==     in use at exit: 0 bytes in 0 blocks
==115057==   total heap usage: 1 allocs, 1 frees, 4 bytes allocated
==115057== 
==115057== All heap blocks were freed -- no leaks are possible
==115057== 
==115057== For lists of detected and suppressed errors, rerun with: -s
==115057== ERROR SUMMARY: 1 errors from 1 contexts (suppressed: 0 from 0)
