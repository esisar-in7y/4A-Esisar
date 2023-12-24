#include "printlib.h"

int main() {
    int n, u;
    n = 6;
    while (n > 1) {
        n = n - 1;
    }
    println_int(n);
    return 0;
}

// EXPECTED
// 1