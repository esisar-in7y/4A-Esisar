#include "printlib.h"

int main(){
    int n,r;
    n = 10;
    r = n%0;
    println_int(r);
    return 0;
}

// EXPECTED
// Modulo by 0
// EXITCODE 1
