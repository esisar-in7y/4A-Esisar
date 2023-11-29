#include "printlib.h"

int main() {
    
    int x,y,z;
    x=8;
    y=2;
    z = x-y;
    println_int(z);
    z = x*y;
    println_int(z);
    z = x/y;
    println_int(z);
    z = x%y;
    println_int(z);
    println_int(-x);
    return 0;
}

// EXPECTED
// 6
// 16
// 4
// 0
// -8