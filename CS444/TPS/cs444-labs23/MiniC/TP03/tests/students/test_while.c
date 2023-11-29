#include "printlib.h"

int main(){
    
    int p,n;
    n = 2;
    p = 0;

    while (p < 10) {
        n = n + 4;
        p = p + 1;
    } 

    println_int(n);

    return 0;
}

// EXPECTED
// 42
