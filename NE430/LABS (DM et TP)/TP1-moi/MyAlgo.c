/* Oui */
#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <stdbool.h>

typedef struct node {
	struct node* zero;
	struct node* one;
	unsigned int entry;
} Noeud;


Noeud tree_root = {NULL,NULL,0};


#define tc_clear_screen() puts("\x1B[2J")
#define tc_move_cursor(X, Y) printf("\033[%d;%dH", Y, X)
void print_tree(){
	int x,y,yf;
	void print_tree_aux(Noeud* n){
		//printf("[%ld] 0:%ld | 1:%ld\n",n,n->zero,n->one);
		if(n->zero) {
			printf("0"); x++; yf=1;
			print_tree_aux(n->zero);
		}
		if(n->one) {
			printf("1"); x++; yf=1;
			print_tree_aux(n->one);
		}
		if(yf) {
			y++; yf=0;
			tc_move_cursor(70, y); printf("[");
		}
		x--;
		tc_move_cursor(x, y);
	}
	x=71; y=5; yf=1;
	tc_move_cursor(70, y); printf("[");
	//tc_clear_screen();
	print_tree_aux(&tree_root);
}
	

void free_tree_aux(Noeud* n){
	if(n){
		free_tree_aux(n->zero);
		free_tree_aux(n->one);
		free(n);
	}
	return;
}
void free_tree(){
	free_tree_aux(tree_root.zero);
	free_tree_aux(tree_root.one);
}


void tree_stat(){
	int noeud_vide;
	int noeuds_total;
	void aux(Noeud* n){
		if(n){
			if(!n->entry)
				noeud_vide++;
			noeuds_total++;
			aux(n->zero);
			aux(n->one);
		}
	}
	noeud_vide=noeuds_total=0;
	aux(&tree_root);
	printf("noeuds vide: %d\n",noeud_vide);
	printf("noeuds total: %d\n",noeuds_total);
}


/* InitMyAlgo */
void initMyAlgo(){
}
/* InsertMyAlgo */
void insertMyAlgo(unsigned int addr,unsigned int netmask,unsigned int gw)
{
	Noeud* n = &tree_root;
	while(netmask){
		if(addr&0x80000000) // Case 1
			n = n->one ? n->one : (n->one=calloc(1,sizeof(Noeud)));
		else //	---- ---- - Case 0
			n = n->zero ? n->zero : (n->zero=calloc(1,sizeof(Noeud)));
			
		netmask<<=1;
		addr<<=1;
	}
	n->entry = gw;
}
/* lookupMyAlgo */
unsigned int lookupMyAlgo(unsigned int addr)
{
	Noeud* n;//ode
	Noeud* b;//ackup
	b=n=&tree_root;
	while(n){
		if (n->entry) b = n;
		if(addr&0x80000000) n=n->one;
		else n=n->zero;
		addr<<=1;
	}
	return b->entry;
	
}

