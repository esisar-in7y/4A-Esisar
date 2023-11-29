#include "printlib.h"

int main()
{
    int x;
    int y;
    x = 10;
    y = 20;
    if (x > 5)
    {
        if (y > 15)
        {
            println_int(x);
            println_int(y);
        }
    }
    return 0;
}
// EXPECTED
// 10
// 20