#include "printlib.h"

int main() {
    int x, y;
    x = 5;
    y = 10;
    if (x > y) {
        y = x + 1;
    }
    println_int(y);
    return 0;
}

// EXPECTED
// 10