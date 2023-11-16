sampleN.c: In function ‘main’:
sampleN.c:7:19: warning: format ‘%p’ expects argument of type ‘void *’, but argument 2 has type ‘int *’ [-Wformat=]
    7 |   printf("answer@%p=%d\n",&answer,answer);
      |                  ~^       ~~~~~~~
      |                   |       |
      |                   void *  int *
      |                  %ls
sampleN.c:8:19: warning: format ‘%p’ expects argument of type ‘void *’, but argument 2 has type ‘char (*)[40]’ [-Wformat=]
    8 |   printf("buffer@%p<",&buffer); gets(buffer);
      |                  ~^   ~~~~~~~
      |                   |   |
      |                   |   char (*)[40]
      |                   void *
sampleN.c:8:33: warning: implicit declaration of function ‘gets’; did you mean ‘fgets’? [-Wimplicit-function-declaration]
    8 |   printf("buffer@%p<",&buffer); gets(buffer);
      |                                 ^~~~
      |                                 fgets
sampleN.c:9:19: warning: format ‘%p’ expects argument of type ‘void *’, but argument 2 has type ‘char (*)[40]’ [-Wformat=]
    9 |   printf("buffer@%p>%s\n",&buffer,buffer);
      |                  ~^       ~~~~~~~
      |                   |       |
      |                   void *  char (*)[40]
sampleN.c:10:19: warning: format ‘%p’ expects argument of type ‘void *’, but argument 2 has type ‘int *’ [-Wformat=]
   10 |   printf("answer@%p=%#x\n",&answer,answer);
      |                  ~^        ~~~~~~~
      |                   |        |
      |                   void *   int *
      |                  %ls
sampleN.c:4:15: warning: unused parameter ‘argc’ [-Wunused-parameter]
    4 | int main (int argc, char* argv[]) {
      |           ~~~~^~~~
sampleN.c:4:27: warning: unused parameter ‘argv’ [-Wunused-parameter]
    4 | int main (int argc, char* argv[]) {
      |                     ~~~~~~^~~~~~
/usr/bin/ld: /tmp/ccz9KpjP.o: in function `main':
sampleN.c:(.text+0x56): warning: the `gets' function is dangerous and should not be used.
