#include <iostream>
#include "greedyWrap.hpp"

int main (void)
{
  std::stringstream ss;
  std::string test = "test string";
  
  ss << "This is a sample of how GreedyWrap works. The defualt word wrapping length is 80 characters, but you can easy change that to suit your needs. GreedyWrap properly handles new line characters and tab characters. Hopefully now that all the bugs seems to be sorted out someone other than me will find it usefull!\n";
  
  GreedyWrap wrap;
  std::cout << wrap.lineWrap(ss);
  
  
  return 0;
}
