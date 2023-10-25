	.file	"ex1.c"
	.option nopic
	.attribute arch, "rv64i2p0_m2p0_a2p0_f2p0_d2p0_c2p0"
	.attribute unaligned_access, 0
	.attribute stack_align, 16
# GNU C17 (g5964b5cd7) version 11.1.0 (riscv64-unknown-elf)
#	compiled by GNU C version 9.4.0, GMP version 6.2.0, MPFR version 4.0.2, MPC version 1.1.0, isl version none
# warning: GMP header version 6.2.0 differs from library version 6.2.1.
# warning: MPFR header version 4.0.2 differs from library version 4.1.0.
# warning: MPC header version 1.1.0 differs from library version 1.2.0.
# GGC heuristics: --param ggc-min-expand=100 --param ggc-min-heapsize=131072
# options passed: -mtune=rocket -march=rv64imafdc -mabi=lp64d -march=rv64imafdc
	.text
	.section	.rodata
	.align	3
.LC0:
	.string	"%d\n"
	.text
	.align	1
	.globl	main
	.type	main, @function
main:
	addi	sp,sp,-32	#,,
	sd	ra,24(sp)	#,
	sd	s0,16(sp)	#,
	addi	s0,sp,32	#,,
# ex1.c:7:   int x = 41;
	li	a5,41		# tmp74,
	sw	a5,-20(s0)	# tmp74, x
# ex1.c:8:   x = x+1;
	lw	a5,-20(s0)		# tmp77, x
	addiw	a5,a5,1	#, tmp75, tmp76
	sw	a5,-20(s0)	# tmp75, x
# ex1.c:9:   printf("%d\n",x);
	lw	a5,-20(s0)		# tmp78, x
	mv	a1,a5	#, tmp78
	lui	a5,%hi(.LC0)	# tmp79,
	addi	a0,a5,%lo(.LC0)	#, tmp79,
	call	printf		#
# ex1.c:11:   return 0;
	li	a5,0		# _5,
# ex1.c:12: }
	mv	a0,a5	#, <retval>
	ld	ra,24(sp)		#,
	ld	s0,16(sp)		#,
	addi	sp,sp,32	#,,
	jr	ra		#
	.size	main, .-main
	.ident	"GCC: (g5964b5cd7) 11.1.0"
