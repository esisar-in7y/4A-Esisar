#include<stdio.h>
#include<stdlib.h>
#include<string.h>

void init(char* b) {
  strcpy(b,"Hello !");
}

void do_stuff(char* b) {
  puts(b);
}

int main() {
  void *a,*b,*c,*d,*e;

  a = malloc(10);
  printf("a = %p\n",a);
  free(a);
  b = malloc(10);
  printf("b = %p\n",b);
  init(b);
  strcpy(a,"Goodbye!");
  do_stuff(b);
  free(b);
  return 0;
}

// Local Variables:
// compile-command: "gcc Vuln-use-after-free.c && ./a.out"
// End:
