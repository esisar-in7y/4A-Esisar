#include "MyAlgo.h"
#define MASK 0xE0000000

node root = {NULL, 0, 0};

void initMyAlgo() {}

node *add_node(node *parent_node, char index)
{
    if (index < parent_node->num_childs)
    {
        if (parent_node->childs[index] == NULL)
            (parent_node->childs[index] = calloc(1, sizeof(node)));
        return parent_node->childs[index];
    }
    else
    {
        if (parent_node->childs == NULL)
        {
            parent_node->childs = calloc(index + 1, sizeof(node *));
        }
        else
        {
            parent_node->childs = realloc(parent_node->childs, (index + 1) * sizeof(node *));
        }
        for (int i = parent_node->num_childs; i < index; i++)
            parent_node->childs[i] = NULL;
        parent_node->childs[index] = calloc(1, sizeof(node));
        parent_node->num_childs = index + 1;
        return parent_node->childs[index];
    }
}

void insertMyAlgo(unsigned int addr, unsigned int netmask, unsigned int gw)
{
    node *current_node = &root;
    // printf("%u %u\n", addr,netmask);
    // printf("%d.%d.%d.%d\n", (addr >> 24) & 0xff, (addr >> 16) & 0xff, (addr >> 8) & 0xff, addr & 0xff);
    while (netmask > 0)
    {
        current_node = add_node(current_node, (addr & MASK) >> 29);
        netmask = netmask << 3;
        addr = addr << 3;
    }
    current_node->gw = gw;
}

unsigned int lookupMyAlgo(unsigned int addr)
{
    unsigned int addr_copy = addr;
    node *current_node = &root;
    node* tmp=NULL;
    unsigned int gw = 0;
    unsigned char index;
    unsigned char index2;
    while (current_node != NULL)
    {
        index = (addr_copy & MASK) >> 29;
        for(int i = 0;i<2;i++){
            index2 = index & (0b1100>>i);
            if (current_node->num_childs > index2)
            {
                tmp = current_node->childs[index2];
                if(tmp!=NULL && tmp->gw){
                    gw = tmp->gw;
                }
            }
        }
        if (current_node->num_childs > index && current_node->childs[index] != NULL){
            current_node = current_node->childs[index];
        }else{
            break;
        }
        if (current_node->gw)
        {
            gw = current_node->gw;
        }
        addr_copy <<= 3;
    }
    printf("%d.%d.%d.%d %d.%d.%d.%d\n", (addr >> 24) & 0xff, (addr >> 16) & 0xff, (addr >> 8) & 0xff, addr & 0xff, (gw >> 24) & 0xff, (gw >> 16) & 0xff, (gw >> 8) & 0xff, gw & 0xff);
    return gw;
}
