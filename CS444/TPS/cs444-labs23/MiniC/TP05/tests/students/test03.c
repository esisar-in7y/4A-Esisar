#include "printlib.h"

int main() {
    int x, y, z;
    x = 1;
    y = 2;
    z = 3;
    if (x > 0) {
        y = x + 1;
    } else {
        z = x - 1;
    }
    println_int(y+z);
    return 0;
}

// EXPECTED
// 5