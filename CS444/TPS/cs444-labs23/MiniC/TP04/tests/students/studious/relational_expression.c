#include "printlib.h"

int main()
{
    int x;
    int y;
    bool z;
    x = 5;
    y = 3;
    z = (x > y);
    println_bool(z);
    return 0;
}

// EXPECTED
// 1