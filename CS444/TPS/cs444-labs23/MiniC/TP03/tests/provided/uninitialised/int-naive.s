##Automatically generated RISCV code, MIF08 & CAP
##naive allocation version


##prelude

        .text
        .globl main
main:
        li t0, 16
        sub sp, sp, t0
        sd ra, 0(sp)
        sd fp, 8(sp)
        add fp, sp, t0


##Generated Code
        # Replaced li temp_0, 0
        li t6, 0
        # (stat (print_stat println_int ( (expr (atom x)) ) ;))
        # Replaced mv a0, temp_0
        mv a0, t6
        # Replaced call println_int
        call println_int
        # (stat (assignment x = (expr (expr (atom x)) + (expr (atom 1)))) ;)
        # Replaced li temp_1, 1
        li t5, 1
        # Replaced add temp_2, temp_0, temp_1
        add t4, t6, t5
        # Replaced mv temp_0, temp_2
        mv t6, t4
        # (stat (print_stat println_int ( (expr (atom x)) ) ;))
        # Replaced mv a0, temp_0
        mv a0, t6
        # Replaced call println_int
        call println_int
        # Return at end of function:
        # Replaced li a0, 0
        li a0, 0


##postlude

        ld ra, 0(sp)
        ld fp, 8(sp)
        li t0, 16
        add sp, sp, t0
        ret

lbl_div_by_zero_0_main:
        la a0, lbl_div_by_zero_0_main_msg
        call println_string
        li a0, 1
        call exit

lbl_div_by_zero_0_main_msg:  .string "Division by 0"
