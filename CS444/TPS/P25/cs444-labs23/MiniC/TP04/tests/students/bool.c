#include "printlib.h"

int main() {
    
    int x,y;
    bool n;
    x=3;
    y=5;
    if (x<y) {
        n=true;
    }else{
        n=false;
    }
    println_bool(n);
    if (x>y) {
        n=true;
    }else{
        n=false;
    }
    println_bool(!n);
    return 0;
}

// EXPECTED
// 1
// 1