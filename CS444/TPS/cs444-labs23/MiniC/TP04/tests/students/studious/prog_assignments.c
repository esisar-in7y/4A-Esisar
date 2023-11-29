#include "printlib.h"

int main()
{
    int x;
    int y;
    x = 5;
    y = 10;
    println_int(x);
    println_int(y);
    return 0;
}
// EXPECTED
// 5
// 10