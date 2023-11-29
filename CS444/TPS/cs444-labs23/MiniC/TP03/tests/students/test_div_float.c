#include "printlib.h"

int main(){
    float n,a,r;
    n = 10.0;
    a = 5.0; 
    r = n / a;
    println_float(r);
    return 0;
}

// EXPECTED
// 2.00

