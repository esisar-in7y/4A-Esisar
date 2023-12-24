#include "printlib.h"

int main()
{
    int a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z;
    a=1;b=2;c=3;d=4;e=5;f=6;g=7;h=8;i=9;j=10;k=11;l=12;m=13;n=14;o=15;p=16;q=17;r=18;s=19;t=20;u=21;v=22;w=23;x=24;y=25;z=26;
    if(a==1)
    {
        if(b==2)
        {
            z=z+1;
        }
        else
        {
            z=z+2;
        }
    }
    else
    {
        z=z+4;
    }
    if(d==4){
        if(a==1 && b==2 && c==3 && d==4 && e==5 && f==6 && g==7 && h==8 && i==9 && j==10 && k==11 && l==12 && m==13 && n==14 && o==15 && p==16 && q==17 && r==18 && s==19 && t==20 && u==21 && v==22 && w==23 && x==24 && y==25 && z==26){
            z=z+8;
        }
        else{
            z=z+16;
        }
    }
    println_int(z);
    return 0;
}

// EXPECTED
// 43