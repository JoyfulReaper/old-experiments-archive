CC = g++
#CC = clang++
CFLAGS = -c -Wall -Wextra -std=c++11 -ggdb -O0 
#CFLAGS = -c -std=c++11 -march=native -O2

all: test

test: luaTest.o
	$(CC) luaTest.o `pkg-config lua --libs` -o test
	
luaTest.o: luaTest.cpp
	$(CC) $(CFLAGS) luaTest.cpp
	
clean:
	rm -rf *.o test