// This program expects 2 integer arguments 
// Find the winning values ....


#include <stdio.h>
#include <stdlib.h>

int main (int argc, char *argv[]) 
{ 
  char x=0 ;
  char t1[8] ;
  int i;
  for (i=0;i<=atoi(argv[2]);i++) 
	  t1[i]= atoi(argv[1]) ;
  if (x != 0) 
	  printf("You win !\n") ;
  else 
	  printf("You lose ...\n") ;
  return 0 ;
}

