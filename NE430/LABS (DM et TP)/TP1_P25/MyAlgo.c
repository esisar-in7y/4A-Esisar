// real    0m0.836s
#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <stdbool.h>
#define MASK 0x80000000
typedef struct _node
{
    struct _node *childs[2];
    unsigned int gw;
} node;

node tree_root = {NULL, NULL, 0};

void initMyAlgo() {}

node *add_node(node *parent_node, bool is_1)
{
    node *new_node = calloc(1, sizeof(node));
    node *side_node = parent_node->childs[(int)is_1];
    if (side_node == NULL)
    {
        return (parent_node->childs[(int)is_1] = new_node);
    }
    else
    {
        return side_node;
    }
}

void insertMyAlgo(unsigned int addr, unsigned int netmask, unsigned int gw)
{
    node *current_node = &tree_root;
    while (netmask > 0)
    {
        current_node = add_node(current_node, addr & MASK);
        netmask = netmask << 1;
        addr = addr << 1;
    }
    current_node->gw = gw;
}

unsigned int lookupMyAlgo(unsigned int addr)
{
    unsigned int addr_copy = addr; // on copie l'adresse pour ne pas la modifier
    node *current_node = &tree_root;
    unsigned int gw = 0;
    while (current_node != NULL) // tant qu'on a pas atteint une feuille
    {
        if (current_node->gw)
        {
            gw = current_node->gw; // Sauvegarde de la gateway si elle est définie
        }
        current_node = current_node->childs[(addr_copy & MASK) > 0];
        addr_copy <<= 1;
    }
    printf("%d.%d.%d.%d %d.%d.%d.%d\n", (addr >> 24) & 0xff, (addr >> 16) & 0xff, (addr >> 8) & 0xff, addr & 0xff, (gw >> 24) & 0xff, (gw >> 16) & 0xff, (gw >> 8) & 0xff, gw & 0xff);
    return gw;
}


/* Fonctions supplémentaires */

// supprimer une route
void remove_route(unsigned int addr, unsigned int netmask) {
    node *current_node = &tree_root;
    node *parent_node = NULL;
    unsigned int addr_copy = addr;
    unsigned int netmask_copy = netmask;
    unsigned int index;
    while (netmask_copy > 0) {
        index = (addr_copy & MASK) >> 31;
        parent_node = current_node;
        current_node = current_node->childs[index];
        netmask_copy = netmask_copy << 1;
        addr_copy = addr_copy << 1;
    }
    if (current_node->childs[0] == NULL && current_node->childs[1] == NULL) {
        free(current_node);
        parent_node->childs[index] = NULL;
    }
    else {
        current_node->gw = 0;
    }
}

// Libérer la mémoire allouée pour l'arbre
void freeTree(node *current_node) {
    if (current_node == NULL) {
        return;
    }
    // Récursivement libérer les nœuds fils
    freeTree(current_node->childs[0]);
    freeTree(current_node->childs[1]);

    // Libérer le nœud courant
    free(current_node);
}