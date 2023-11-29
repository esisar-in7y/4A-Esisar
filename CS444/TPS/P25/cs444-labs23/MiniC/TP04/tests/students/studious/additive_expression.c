#include "printlib.h"

int main()
{
    int x;
    int y;
    int z;
    y = 3;
    x = 5;
    z = x + y;
    println_int(z);
    return 0;
}
// EXPECTED
// 8
