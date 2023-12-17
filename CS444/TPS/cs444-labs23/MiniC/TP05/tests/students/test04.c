#include "printlib.h"

int main() {
    int a, b, c;
    a = 1;
    b = 2;
    c = a + b;
    println_int(c);
    return 0;
}

// EXPECTED
// 3