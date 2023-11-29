#include "printlib.h"

int main()
{
    int x;
    int y;
    int z;
    x = 5;
    y = 3;
    z = x * y;
    println_int(z);
    return 0;
}
// EXPECTED
// 15
