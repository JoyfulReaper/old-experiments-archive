#CC = g++
CC = clang++
CFLAGS = -c -Wall -Wextra -std=c++11 -ggdb -O0
#CFLAGS = -c -std=c++11 -march=native -O2

all: test

test: test.o
	$(CC) test.o -o test

test.o: test.cpp LinkedList.cxx LinkedList.h
	$(CC) $(CFLAGS) test.cpp 
	
clean:
	rm -rf *.o test