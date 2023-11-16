==115449== Memcheck, a memory error detector
==115449== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==115449== Using Valgrind-3.16.1 and LibVEX; rerun with -h for copyright info
==115449== Command: ./sampleL.c.gcc_opts.o
==115449== 
==115449== Invalid free() / delete / delete[] / realloc()
==115449==    at 0x48399AB: free (vg_replace_malloc.c:538)
==115449==    by 0x1091F7: main (in /home/userir/Downloads/TP_2023-24/_code/sampleL.c.gcc_opts.o)
==115449==  Address 0x4a33040 is 0 bytes inside a block of size 10 free'd
==115449==    at 0x48399AB: free (vg_replace_malloc.c:538)
==115449==    by 0x1091DF: main (in /home/userir/Downloads/TP_2023-24/_code/sampleL.c.gcc_opts.o)
==115449==  Block was alloc'd at
==115449==    at 0x483877F: malloc (vg_replace_malloc.c:307)
==115449==    by 0x109191: main (in /home/userir/Downloads/TP_2023-24/_code/sampleL.c.gcc_opts.o)
==115449== 
==115449== Conditional jump or move depends on uninitialised value(s)
==115449==    at 0x483BC85: strlen (vg_replace_strmem.c:459)
==115449==    by 0x48CE23F: puts (ioputs.c:35)
==115449==    by 0x109288: main (in /home/userir/Downloads/TP_2023-24/_code/sampleL.c.gcc_opts.o)
==115449== 
a = 0x4a33040
b = 0x4a340d0
c = 0x4a34120
d = 0x4a34170
e = 0x4a341c0

==115449== 
==115449== HEAP SUMMARY:
==115449==     in use at exit: 30 bytes in 3 blocks
==115449==   total heap usage: 6 allocs, 4 frees, 4,146 bytes allocated
==115449== 
==115449== LEAK SUMMARY:
==115449==    definitely lost: 30 bytes in 3 blocks
==115449==    indirectly lost: 0 bytes in 0 blocks
==115449==      possibly lost: 0 bytes in 0 blocks
==115449==    still reachable: 0 bytes in 0 blocks
==115449==         suppressed: 0 bytes in 0 blocks
==115449== Rerun with --leak-check=full to see details of leaked memory
==115449== 
==115449== Use --track-origins=yes to see where uninitialised values come from
==115449== For lists of detected and suppressed errors, rerun with: -s
==115449== ERROR SUMMARY: 2 errors from 2 contexts (suppressed: 0 from 0)
