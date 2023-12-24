#include "printlib.h"

int main()
{
    int n, u, v;
    n = 6;
    v = 0;
    u = 0;
    while (n > 1)
    {
        n = n - 1;
        u = u + 1;
    }
    v = v + 1;
    println_int(v);
    return 0;
}

// EXPECTED
// 1