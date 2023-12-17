#include "printlib.h"

int main() {
    int a, b, c;
    a = 5;
    b = 10;
    if (a < b) {
        c = a + b;
    } else {
        c = a - b;
    }
    println_int(c);
    return 0;
}

// EXPECTED
// 15