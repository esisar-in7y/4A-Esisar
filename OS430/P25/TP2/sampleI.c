#include<stdlib.h>

int main(void)
{
    int *p = malloc(sizeof *p);
    if (p != NULL)
        free(p);
    p = malloc(sizeof *p);
    p = NULL;
    return 0;    
}
