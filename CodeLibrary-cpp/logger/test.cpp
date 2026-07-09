/*
    Logger: test.cpp
    Copyright (C) 2014 Kyle Givler

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.

*/

#include <iostream>
#include <fstream>
#include "logger.hpp"

int main()
{
  Logger log("test");
  
  log.addStream(std::cout);
  log.log("test.cpp");
  
  log.removeStream(std::cout);
  log.log("test2");
  
  log.removeStream(std::cout);
  std::cout << std::endl;
  
  ///////////////////////////////////////////////////
  
  Logger log2("log2");
  log2.log(Level::INFO, "INFO");
  log2.log("NOTINFO");
  
  ///////////////////////////////////////////////////
  
  std::ofstream fout;
  fout.open("test.file", std::ofstream::out | std::ofstream::app);
  Logger flog("flog", Level::WARN, fout);
  flog.log("file log");
  flog.severe("OH NOES!");
  return 0;
}