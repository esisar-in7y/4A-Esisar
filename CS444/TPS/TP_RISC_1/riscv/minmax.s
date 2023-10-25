	.text
	.globl main
main: 	                                                           
	addi	sp,sp,-16
	sd	ra,8(sp)
## TODO Your assembly code there
	la t0, mydata
	ld t1, 0(t0)
	ld t2, 8(t0)
	bge t2, t1, t2plus
t1plus:
	add t3, zero, t1
	j end
t2plus:
	add t3, zero, t2
end:
	la t0, min
	sd t3, 0(t0)
## END TODO - end of user assembly code
	ld	ra,8(sp)
	addi	sp,sp,16
	ret

# Data comes here
	.section	.data
mydata:
	.dword 7
	.dword 42
min:
	.dword 0


