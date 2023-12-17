#include "printlib.h"

int main() {
    int x, y, z;
    x = 5;
    y = 10;
    z = x + y;
    println_int(z);
    return 0;
}

// EXPECTED
// 15