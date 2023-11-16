==115579== Memcheck, a memory error detector
==115579== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==115579== Using Valgrind-3.16.1 and LibVEX; rerun with -h for copyright info
==115579== Command: ./sampleO.c.gcc_opts.o
==115579== 
==115579== Conditional jump or move depends on uninitialised value(s)
==115579==    at 0x483BC85: strlen (vg_replace_strmem.c:459)
==115579==    by 0x48C4B77: __vfprintf_internal (vfprintf-internal.c:1647)
==115579==    by 0x48AFD9A: printf (printf.c:33)
==115579==    by 0x1091B5: main (in /home/userir/Downloads/TP_2023-24/_code/sampleO.c.gcc_opts.o)
==115579== 
buffer@0x1ffefffc30=
answer@0x1ffefffcec=0x2a
==115579== 
==115579== HEAP SUMMARY:
==115579==     in use at exit: 0 bytes in 0 blocks
==115579==   total heap usage: 1 allocs, 1 frees, 4,096 bytes allocated
==115579== 
==115579== All heap blocks were freed -- no leaks are possible
==115579== 
==115579== Use --track-origins=yes to see where uninitialised values come from
==115579== For lists of detected and suppressed errors, rerun with: -s
==115579== ERROR SUMMARY: 1 errors from 1 contexts (suppressed: 0 from 0)
