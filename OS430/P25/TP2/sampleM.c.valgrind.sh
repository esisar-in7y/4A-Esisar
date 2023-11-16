==115482== Memcheck, a memory error detector
==115482== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==115482== Using Valgrind-3.16.1 and LibVEX; rerun with -h for copyright info
==115482== Command: ./sampleM.c.gcc_opts.o
==115482== 
==115482== Invalid write of size 8
==115482==    at 0x109216: main (in /home/userir/Downloads/TP_2023-24/_code/sampleM.c.gcc_opts.o)
==115482==  Address 0x4a33040 is 0 bytes inside a block of size 10 free'd
==115482==    at 0x48399AB: free (vg_replace_malloc.c:538)
==115482==    by 0x1091D5: main (in /home/userir/Downloads/TP_2023-24/_code/sampleM.c.gcc_opts.o)
==115482==  Block was alloc'd at
==115482==    at 0x483877F: malloc (vg_replace_malloc.c:307)
==115482==    by 0x1091AD: main (in /home/userir/Downloads/TP_2023-24/_code/sampleM.c.gcc_opts.o)
==115482== 
==115482== Invalid write of size 1
==115482==    at 0x109219: main (in /home/userir/Downloads/TP_2023-24/_code/sampleM.c.gcc_opts.o)
==115482==  Address 0x4a33048 is 8 bytes inside a block of size 10 free'd
==115482==    at 0x48399AB: free (vg_replace_malloc.c:538)
==115482==    by 0x1091D5: main (in /home/userir/Downloads/TP_2023-24/_code/sampleM.c.gcc_opts.o)
==115482==  Block was alloc'd at
==115482==    at 0x483877F: malloc (vg_replace_malloc.c:307)
==115482==    by 0x1091AD: main (in /home/userir/Downloads/TP_2023-24/_code/sampleM.c.gcc_opts.o)
==115482== 
a = 0x4a33040
b = 0x4a340d0
Hello !
==115482== 
==115482== HEAP SUMMARY:
==115482==     in use at exit: 0 bytes in 0 blocks
==115482==   total heap usage: 3 allocs, 3 frees, 4,116 bytes allocated
==115482== 
==115482== All heap blocks were freed -- no leaks are possible
==115482== 
==115482== For lists of detected and suppressed errors, rerun with: -s
==115482== ERROR SUMMARY: 2 errors from 2 contexts (suppressed: 0 from 0)
