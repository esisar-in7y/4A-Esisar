# Code de César en RISCV
# CS444, binôme : ROUGÉ, SONKO
	.section .text
	.globl main
main:
        addi    sp,sp,-16 
        sd      ra,8(sp)
	## Assembly code here
	la a0, machaine    # convention: a0 contient le premier argument
	call println_string
	# Exemple : appel à la fonction Cesar
	call Caesar
	## end of user assembly code
        ld      ra,8(sp)
        addi    sp,sp,16
        ret

Caesar:
	addi    sp,sp,-32 
        sd      ra,8(sp)
        sd      s1,16(sp)
		sd		s2,24(sp)
	## code "fonctionnel" de la routine
	la t0, dec	# On va chercher l'adresse de la variable dec
	lb s2, 0(t0)# Puis on met la valeur de dec dans s2
	mv s1, a0 # l'argument de la routine contient l'adresse de début de la chaine
loop:	
	lb t5, 0(s1) 	# caractère pointé par t5 dans s1.
	beq t5,zero,exit# si t5 = 0 ('\0') c'est la fin de la chaine 
	add a0,t5,s2 	# incrémentation du caractere de dec, on le met dans a0
	# (Attention, pas de modulo)
	# Mais on va dire que ça va
	call print_char # on print le caractère dans a0
	addi s1, s1,1 	# incrément de l'adresse (on pointe vers le prochain char de la chaine)
	j loop 
exit:
	call newline	# On revient à la ligne
	addi a0, x0, 0  # On renvoi 0 (a0 registre de retour)
	## fin du code fonctionnel (on restore la pile)
        ld      ra,8(sp)
        ld      s1,16(sp)
        ld      s2,24(sp)
        addi    sp,sp,32	
	ret

	
	.section .rodata
machaine:
	.string "Hello world!"
dec:
	.byte 4
	
