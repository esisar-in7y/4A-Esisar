#include "printlib.h"

int main() {
    
    int x,y;
    bool n;
    x=3;
    y=5;
    if (x<y && true) {
        n=true;
    }
    println_bool(n);
    if (x==y || true) {
        n=true;
    }
    println_bool(n);
    n=(x==y);
    println_bool(n);
    return 0;
}

// EXPECTED
// 1
// 1
// 0