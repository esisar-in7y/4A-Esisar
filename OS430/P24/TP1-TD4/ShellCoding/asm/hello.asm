; ----------------------------------------------------------------------------------------
; Exec /bin/sh using system calls. Runs on 64-bit Linux only.
; To assemble and run:
;
;     nasm -felf64 hello.asm && ld hello.o && ./a.out
; ----------------------------------------------------------------------------------------


        global    _start

        section   .text
_start: ; Syscall exec
; On prépare:	exec("/bin/sh",["/bin/sh",NULL],NULL)
	mov rax, 59				; system call for exit
    	mov rdi, command	; nom de la command
    	mov rsi, argv		; liste des arguments
    	mov rdx, 0 			; NULL
	syscall         ;appel systeme

	mov rax, 60                 ; system call for exit
    xor rdi, rdi                ; exit code 0
    syscall                     ; invoke operating system to exit

          section   .data
	command: db "/bin/sh",0x0
	last_arg: dq 0x0
	argv: dq command, 0x0

