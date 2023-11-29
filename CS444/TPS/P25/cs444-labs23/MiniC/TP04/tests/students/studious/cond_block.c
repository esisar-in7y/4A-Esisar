#include "printlib.h"

int main()
{
    int x = 5;
    if (x > 5)
    {
        println_int(-x);
    }else{
        println_int(x);
    }
    return 0;
}
// EXPECTED
// 5