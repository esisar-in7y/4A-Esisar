/* Oui */
#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <stdbool.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <string.h> 

typedef struct __attribute__((packed)) {
	unsigned char mask;
	unsigned int gw;
	void * child;
} Stage1;

typedef struct __attribute__((packed)) liste {
	unsigned char mask;
	unsigned char key;
	unsigned int gw;
	struct liste* next;
} Stage4;

typedef struct node {
	struct node* zero;
	struct node* one;
	Stage4* child;
	unsigned int gw;
} Stage3;



Stage1 tree[256][257] = {0};

void insert_liste(Stage4* l,unsigned char k,unsigned char m,unsigned int gw){
	Stage4 * bak;
	while(l){
		if(l->key==0){
			l->key=k;
			l->mask=m;
			return;
		}
		if(l->key==k) if(l->mask < m || l->mask == 0) {
			l->key=k;
			l->mask=m;
			return;
		}
		bak = l; l = l->next;
	}
	bak->next = calloc(1,sizeof(Stage4));
	l=bak->next;
	l->key=k;
	l->mask=m;
	return;
}


/* InitMyAlgo */
void initMyAlgo(){
}
/* InsertMyAlgo */
void insertMyAlgo(unsigned int addr,unsigned int netmask,unsigned int gw)
{
	
	
	if((netmask>>24)==0xFF){ // Le masque est plus grand que 8
		if(((netmask>>16)&0xFF)==0xFF){ // Le masque est plus grand que 16
			unsigned char i,a,b,m;
			Stage3* p;
			//printf("Le masque est plus grand que 16\n");
			a=(addr>>16)&0xFF;
			b=(addr>>24)&0xFF;
			
			tree[b][a].child = ( tree[b][a].child ? tree[b][a].child : calloc(1,sizeof(Stage3)) );
			p = tree[b][a].child;
			m=(netmask>>8)&0xFF;
			a=(addr>>8)&0xFF;
			while(m){
				if(addr&0x8000) // Case 1
					p = p->one ? p->one : (p->one=calloc(1,sizeof(Stage3)));
				else //	---- ---- - Case 0
					p = p->zero ? p->zero : (p->zero=calloc(1,sizeof(Stage3)));
					
				m<<=1;
				a<<=1;
			}
			m=netmask&0xFF;
			if(m){ // Le masque est plus grand que 24
				unsigned char k;
				Stage4 * l;
				//printf("Le masque est plus grand que 24\n");
				k = m&addr;
				while(! ((netmask>>i)&1) ) i++;
				
				l=p->child;
				
				for(int j=k; (j&k)>>i == j>>i; j++){
					//printf("j:%u",j);
					insert_liste(l,j,m,gw);
				} //puts("");
				
			}
			else { // Le masque est entre 16 et 24
				p->gw = gw;
			}
			
		}
		else { // Le masque est entre 8 et 16
			unsigned char i,a,b;
			//printf("Le masque est entre 8 et 16\n");
			i=0;
			while(! ((netmask>>i)&1) ) i++;
			a=(addr>>16)&(netmask>>16);
			b=(addr>>24)&0xFF;
			i-=16;
			//printf("b:%u, a:%u, i:%d\n",b,a,i);
			for(int j=a; (j&a)>>i == j>>i; j++){
				//printf("j:%u",j);
				if(tree[b][j].mask > i || tree[b][j].mask == 0 ) tree[b][j].gw = gw;
			} //puts("");
		}
	}
	else { // Le masque est plus petit que 8
		//printf("Le masque est plus petit que 8\n");
		unsigned char i,a;
		i=0;
		while(! ((netmask>>i)&1) ) i++;
		a=(addr>>24)&(netmask>>24);
		i-=24;
		//printf("a:%u, i:%d\n",a,i);
		for(int j=a; (j&a)>>i == j>>i; j++){ //populate every corresponding
			//printf("j:%u",j);
			if(tree[j][256].mask > i || tree[j][256].mask == 0) tree[j][256].gw = gw;
		}
	}
	
	
	
	
}

unsigned int search_list(Stage4* l, unsigned char k){
	while(l){
		if (l->key == k)
			return l->gw;
	}
	return 0;
}

/* lookupMyAlgo */
unsigned int lookupMyAlgo(unsigned int addr)
{
	unsigned char a,b,c,d,i;
	unsigned int v1,v2,v3,v4;
	Stage3* n,*p;
	Stage4* l;
	a=(addr>>24)&0xFF;
	b=(addr>>16)&0xFF;
	c=(addr>>8)&0xFF;
	d=addr&0xFF;
	i=0;
	while(!(v1=tree[(a>>i)<<i][256].gw) && i<8)i++;
	i=0;
	while((v2=tree[a][(b>>i)<<i].gw)==0 && i<8){i++;
		//printf("b:%d",(b>>i)<<i);
	}
	
	p=n= (tree[a][b].child);
	v3=0;
	while(n){
		if (n->gw) v3 = n->gw;
		p=n;
		if(c&0x80) n=n->one;
		else n=n->zero;
		addr<<=1;
	}
	v4=0;
	if(p){
		l=p->child; i=0;
		if(l) while(!(v4=search_list(l,(d>>i)<<i)) && i<8)i++;
	}
	//printf("v4 ");
	if(v4) return v4;
	//printf("v3 ");
	if(v3) return v3;
	//printf("v2 ");
	if(v2) return v2;
	//printf("v1 ");
	return v1;
}


void mano(){
	struct in_addr c;
	unsigned int a;
	inet_aton("170.168.0.0",&c);
	a=htonl(c.s_addr);
	puts("\nmano");
	a=lookupMyAlgo(a);
	printf("1:%X\n",a);
}
