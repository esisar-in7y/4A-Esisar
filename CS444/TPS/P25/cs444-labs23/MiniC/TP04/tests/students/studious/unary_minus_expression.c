#include "printlib.h"

int main()
{
    int x;
    int y;
    x = 5;
    y = -x;
    println_int(y);
    return 0;
}
// EXPECTED
// -5