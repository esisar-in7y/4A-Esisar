#include "printlib.h"

int main()
{
    bool x;
    bool z;
    x = true;
    z = !x;
    println_bool(z);
    return 0;
}
// EXPECTED
// 0