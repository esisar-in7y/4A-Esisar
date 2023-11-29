#include "printlib.h"

int main()
{
    int i;
    int j;
    j = 0;
    i = 0;
    while (i < 5)
    {
        j = 0;
        while (j < 5)
        {
            println_int(i);
            println_int(j);
            j = j + 1;
        }
        i = i + 1;
    }
    return 0;
}
// EXPECTED
// 0
// 0
// 0
// 0
// 0
// 1
// 0
// 1
// 0
// 1
// 0
// 1
// 2
// 0
// 2
// 0
// 2
// 0
// 2
// 3
// 0
// 3
// 0
// 3
// 0
// 3
// 4
// 0
// 4
// 0
// 4
// 0
// 4