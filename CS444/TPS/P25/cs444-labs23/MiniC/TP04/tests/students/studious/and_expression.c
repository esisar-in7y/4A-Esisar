#include "printlib.h"

int main()
{
    bool x;
    bool y;
    bool z;
    x = true;
    y = false;
    z = x && y;
    println_bool(z);
    return 0;
}
// EXPECTED
// 0