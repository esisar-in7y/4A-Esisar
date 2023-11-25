#include<stdio.h>
#include<string.h>


void valide_ex(char *buffer){

	if(strncmp(buffer, "RoPchain\x0", strlen("RoPchain\x0")) == 0)
		printf("Overflow, go to the next exercise\n");	

}

void vuln(){

	char buf[16];
	gets(buf);
	printf("Return... \n");
}

int main(int argc, char **argv){

	vuln();

	printf("End of program!\n");

return 0;
}
