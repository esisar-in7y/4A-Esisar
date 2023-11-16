sampleO.c: In function ‘main’:
sampleO.c:8:19: warning: format ‘%p’ expects argument of type ‘void *’, but argument 2 has type ‘char (*)[180]’ [-Wformat=]
    8 |   printf("buffer@%p=%s\n",&buffer,buffer);
      |                  ~^       ~~~~~~~
      |                   |       |
      |                   void *  char (*)[180]
sampleO.c:9:19: warning: format ‘%p’ expects argument of type ‘void *’, but argument 2 has type ‘int *’ [-Wformat=]
    9 |   printf("answer@%p=%#x\n",&answer,answer);
      |                  ~^        ~~~~~~~
      |                   |        |
      |                   void *   int *
      |                  %ls
