==115515== Memcheck, a memory error detector
==115515== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==115515== Using Valgrind-3.16.1 and LibVEX; rerun with -h for copyright info
==115515== Command: ./sampleN.c.gcc_opts.o
==115515== 
answer@0x1ffefffcec=42
buffer@0x1ffefffcc0<buffer@0x1ffefffcc0>
answer@0x1ffefffcec=0x2a
==115515== 
==115515== HEAP SUMMARY:
==115515==     in use at exit: 0 bytes in 0 blocks
==115515==   total heap usage: 2 allocs, 2 frees, 5,120 bytes allocated
==115515== 
==115515== All heap blocks were freed -- no leaks are possible
==115515== 
==115515== For lists of detected and suppressed errors, rerun with: -s
==115515== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 0 from 0)
