#include<stdlib.h>

int main(void)
{
    int *p = malloc(sizeof *p);
    if (p != NULL)
    {
        free(p);
        *p = 1;
    }
    return 0;    
}
