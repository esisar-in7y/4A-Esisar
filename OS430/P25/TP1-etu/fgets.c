#include  <stdio.h>
#include  <string.h>
#include  <stdlib.h>

unsigned int size=12 ;

_Bool IsPasswordOK(void)
{
	char Password[size];
	fgets(Password, size, stdin);
	if (Password[strlen(Password) - 1] == '\n')
		Password[strlen(Password) - 1] = 0;
	return 0 == strcmp(Password, "goodpass");
}

int main(void)
{
	int status1, status2;
	puts("Enter  password  x  2");
	status1 = IsPasswordOK();
	status2 = IsPasswordOK();
	if (status1 == 0)
		puts("Access  denied");
	else
		puts("OK");
	if (status2 == 0)
		puts("Access  denied");
	else
		puts("OK");
}
