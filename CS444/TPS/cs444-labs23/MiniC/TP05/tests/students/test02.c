#include "printlib.h"

int main()
{
   int a, b;
   a = 5;
   b = 10;
   if (a > b)
   {
      println_int(a);
   }
   else
   {
      println_int(b);
   }
   return 0;
}

// EXPECTED
// 10