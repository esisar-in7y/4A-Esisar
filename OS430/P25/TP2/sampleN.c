#include<stdio.h>
#include<string.h>

int main (int argc, char* argv[]) {
  int answer=42;
  char buffer[40];
  printf("answer@%p=%d\n",&answer,answer);
  printf("buffer@%p<",&buffer); gets(buffer);
  printf("buffer@%p>%s\n",&buffer,buffer);
  printf("answer@%p=%#x\n",&answer,answer);
  return 0;
}

/* compile with :
   gcc -std=c11 -fstack-protector
   gcc -std=c99 -fstack-protector
   gcc -std=c99 -fno-stack-protector
   clang -std=c99 -fno-stack-protector
   clang -std=c99 -fstack-protector
*/
