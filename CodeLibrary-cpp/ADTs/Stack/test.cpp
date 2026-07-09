/**
* Test program for Stack ADT
* @file test.cpp
* @author Kyle Givler
*/

#include <iostream>
#include "stack.h"

using namespace std;

int main()
{
    Stack<int> mStack;
    
    cout << "Testing isEmpty(): " << mStack.isEmpty() << endl;
    cout << "Testing push(1,2,3) \n";
    mStack.push(1);
    mStack.push(2);
    mStack.push(3);
    cout << "Testing isEmpty(): " << mStack.isEmpty() << endl;
    cout << "Testing push and pop\n";
    
    while(!mStack.isEmpty())
    {
	cout << mStack.peek() << endl;
	mStack.pop();
    }
    
    mStack.push(4);
    mStack.push(5);
    mStack.push(6);
    Stack<int> mStack2(mStack);
    cout << "Testing Copy Constuctor (expect 6,5,4)\n";
    
    while(!mStack.isEmpty())
    {
	cout << mStack.peek() << endl;
	mStack.pop();
    }
    
    
    mStack.push(1);
    mStack2 = mStack; 
    cout << "\nTesting = operator\n";
    while(!mStack2.isEmpty())
    {
	cout << mStack2.peek() << endl;
	mStack2.pop();
    }
    mStack.push(2);
    cout << "\nTesting !=/== operators:\n";
    cout << "Expect 1: " << (mStack != mStack2) << endl;
    
    cout << "Testing << operator\n";
    mStack2.push(66);
    mStack2.push(67);
    mStack2.push(66);
    cout << mStack2;
}