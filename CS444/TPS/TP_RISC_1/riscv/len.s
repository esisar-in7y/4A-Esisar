# Exemple appel de fonction en RISCV.
	.text
	.globl main
main:
        addi    sp,sp,-16 
        sd      ra,8(sp)
	## Assembly code here
	la a0, machaine    # convention: a0 contient le premier argument
	call println_string
	# Exemple : appel à la fonction length
	call length
	call print_int
	call newline
	## end of user assembly code
        ld      ra,8(sp)
        addi    sp,sp,16
        ret

length:
	addi    sp,sp,-16 
        sd      ra,8(sp)
	## code "fonctionnel" de la routine
	li t1, -1 # compteur de taille
	mv t3, a0 # l'argument de la routine contient l'adresse de début
loop:	
	lb t5, 0(t3) # caractère courant
	addi t1,t1,1 #incrément du compteur de taille
	addi t3, t3,1 #incrément de l'adresse
	bne t5,zero,loop
	mv a0,t1  # a0 contient le résultat
	## fin du code fonctionnel
        ld      ra,8(sp)
        addi    sp,sp,16	
	ret

	
	.section .rodata
machaine:
	.string "kayuk"
	
