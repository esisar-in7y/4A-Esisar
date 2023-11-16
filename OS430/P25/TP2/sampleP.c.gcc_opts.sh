sampleP.c: In function ‘main’:
sampleP.c:7:19: warning: format ‘%p’ expects argument of type ‘void *’, but argument 2 has type ‘int *’ [-Wformat=]
    7 |   printf("answer@%p=%d\n",&answer,answer);
      |                  ~^       ~~~~~~~
      |                   |       |
      |                   void *  int *
      |                  %ls
sampleP.c:9:19: warning: format ‘%p’ expects argument of type ‘void *’, but argument 2 has type ‘char (*)[40]’ [-Wformat=]
    9 |   printf("buffer@%p>%s\n",&buffer,buffer);
      |                  ~^       ~~~~~~~
      |                   |       |
      |                   void *  char (*)[40]
sampleP.c:10:19: warning: format ‘%p’ expects argument of type ‘void *’, but argument 2 has type ‘int *’ [-Wformat=]
   10 |   printf("answer@%p=%#x\n",&answer,answer);
      |                  ~^        ~~~~~~~
      |                   |        |
      |                   void *   int *
      |                  %ls
