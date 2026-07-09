CC = g++
#CC = clang++
CFLAGS = -c -Wall -Wextra -std=c++11 -ggdb -O0
#CFLAGS = -c -std=c++11 -march=native -O2



all: liblogger.a test

test: test.o
	$(CC) test.o -L=./ -llogger -o test

logger.o: logger.cxx logger.hpp
	$(CC) $(CFLAGS) logger.cxx
	
test.o: test.cpp logger.o logger.hpp
	$(CC) $(CFLAGS) test.cpp
	
liblogger.a: logger.hpp logger.o
	ar -rcs liblogger.a logger.o
	
clean:
	rm -rf *.o liblogger.a test
