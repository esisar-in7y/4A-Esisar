#include "printlib.h"

int main(){
    int n,a,r;
    n = 10;
    a = 0;
    r = n/a; 
    println_int(r);
    return 0;
}

// EXPECTED
// Division by 0
// EXITCODE 1
