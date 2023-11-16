#include "printlib.h"

int main(){
    int p,n;
    n = 8;
    if (n != 8){
        p = 8;
    }
    else {
        p = 14;
    }
    println_int(p);
    return 0;
}

// EXPECTED
// 14
