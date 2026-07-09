# Quick Makefile
CC = g++ --std=c++11

all: test

test: test.o greedyWrap.o
	$(CC) test.o greedyWrap.o -o test

test.o: test.cpp
	$(CC) -c test.cpp

greedyWrap.o: greedyWrap.cxx greedyWrap.hpp
	$(CC) -c greedyWrap.cxx 
	
clean:
	rm -f *.o test
