sampleN.c: In function ‘main’:
sampleN.c:8:33: warning: implicit declaration of function ‘gets’; did you mean ‘fgets’? [-Wimplicit-function-declaration]
    8 |   printf("buffer@%p<",&buffer); gets(buffer);
      |                                 ^~~~
      |                                 fgets
/usr/bin/ld: /tmp/ccomLFom.o: in function `main':
sampleN.c:(.text+0x11c): warning: the `gets' function is dangerous and should not be used.
