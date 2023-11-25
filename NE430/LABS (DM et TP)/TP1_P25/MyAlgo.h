#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <stdbool.h>
typedef struct _node
{
    struct _node **childs;
    char num_childs;
    unsigned int gw;
} node;

void initMyAlgo();

void insertMyAlgo(unsigned int addr,unsigned int netmask,unsigned int gw);

unsigned int lookupMyAlgo(unsigned int addr);
