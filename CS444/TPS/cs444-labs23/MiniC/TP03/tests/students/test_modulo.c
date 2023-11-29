#include "printlib.h"

int main(){
    int n,r;
    n = 10;
    r = n%3;
    println_int(r);
    return 0;
}

// EXPECTED
// 1
