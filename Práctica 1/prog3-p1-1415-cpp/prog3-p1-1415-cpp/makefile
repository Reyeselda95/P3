COMP=g++
OPC=-g -Wall

.PHONY=clean

all: main
 
main: main.cc Coordinate.o Coordinate.h
	$(COMP) $(OPC) main.cc Coordinate.o -I . -o main

Coordinate.o: Coordinate.cc Coordinate.h
	$(COMP) $(OPC) -c Coordinate.cc -I .

clean:
	rm main 
	rm -r *.o

