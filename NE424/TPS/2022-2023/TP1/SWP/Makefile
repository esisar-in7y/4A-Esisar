# GCC 10 defaults to -fno-common, so we need to add -fcommon
CFLAGS= -Wall -fcommon
CC=gcc

all: clean swp

clean:
	rm -f *.o swp

swp: simulator.o lib.o swp.o
	$(CC) $(CFLAGS) -o swp swp.o simulator.o lib.o

swp.o: protocol.h

simulator.o: simulator.h protocol.h

lib.o: simulator.h protocol.h

