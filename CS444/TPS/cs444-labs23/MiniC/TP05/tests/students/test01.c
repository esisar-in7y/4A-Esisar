#include "printlib.h"

int main() {
   int a, b, c, d, e;
   a = 1;
   b = 2;
   c = 3;
   d = 4;
   e = 5;
   println_int(a+b+c+d+e);
   return 0;
}

// EXPECTED
// 15
