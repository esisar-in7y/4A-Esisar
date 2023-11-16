#include<stdio.h>
#include<stdlib.h>
#include<string.h>

// Works with gcc from old debian 9 or old ubuntu,
// fails with  more recent compiler


int main() {
  void *a,*b,*c,*d,*e;
  char *msg="Pwned !!";

  a = malloc(10);
  printf("a = %p\n",a);
  b = malloc(10);
  printf("b = %p\n",b);

  free(a);
  free(b); // double free check fails because of this line
  free(a);

  c = malloc(10);
  printf("c = %p\n",c);
  d = malloc(10);
  printf("d = %p\n",d);
  e = malloc(10);
  printf("e = %p\n",e);

  strcpy(c,msg);
  puts(e);
  return 0;
}

// Local Variables:
// compile-command: "gcc Vuln-double-free.c && ./a.out"
// End:
