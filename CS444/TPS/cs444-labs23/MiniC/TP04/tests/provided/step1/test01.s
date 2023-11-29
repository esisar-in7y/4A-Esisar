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
        # (stat (assignment n = (expr (atom 6))) ;)
        # Replaced li temp_1, 6
        li t5, 6
        # Replaced mv temp_0, temp_1
        mv t6, t5
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
