==115627== Memcheck, a memory error detector
==115627== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==115627== Using Valgrind-3.16.1 and LibVEX; rerun with -h for copyright info
==115627== Command: ./sampleP.c.gcc_opts.o
==115627== 
==115627== Conditional jump or move depends on uninitialised value(s)
==115627==    at 0x483BC85: strlen (vg_replace_strmem.c:459)
==115627==    by 0x48C4B77: __vfprintf_internal (vfprintf-internal.c:1647)
==115627==    by 0x48AFD9A: printf (printf.c:33)
==115627==    by 0x1091B1: main (in /home/userir/Downloads/TP_2023-24/_code/sampleP.c.gcc_opts.o)
==115627== 
answer@0x1ffefffcec=42
buffer@0x1ffefffcc0>
answer@0x1ffefffcec=0x2a
==115627== 
==115627== HEAP SUMMARY:
==115627==     in use at exit: 0 bytes in 0 blocks
==115627==   total heap usage: 1 allocs, 1 frees, 4,096 bytes allocated
==115627== 
==115627== All heap blocks were freed -- no leaks are possible
==115627== 
==115627== Use --track-origins=yes to see where uninitialised values come from
==115627== For lists of detected and suppressed errors, rerun with: -s
==115627== ERROR SUMMARY: 1 errors from 1 contexts (suppressed: 0 from 0)
