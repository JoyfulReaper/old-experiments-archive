/**
* Test program for Bag ADT
* @file test.cpp
* @author Kyle Givler
*/

#include <iostream>
#include "bag.h"

using namespace std;

int main()
{
    Bag<int> mBag;
    
    cout << "size: " << mBag.getSize() << endl;
    cout << "isEmpty(): " << mBag.isEmpty() << endl;
    cout << "adding 1,2,3...\n";
    mBag.add(1);
    mBag.add(2);
    mBag.add(3);
    cout << "size: " << mBag.getSize() << endl;
    cout << "isEmpty(): " << mBag.isEmpty() << endl << endl;
    cout << "Contents: " << mBag;
    Bag<int> mBag2;
    cout << "\nTesting equal operator: ";
    mBag2 = mBag;
    cout << mBag2 << endl;
    cout << "Testing copy constructor: ";
    Bag<int> mBag3(mBag2);
    cout << mBag3 << endl;
    
    cout << "\nTesting remove(2): ";
    mBag3.remove(2);
    cout << mBag3 << endl;
    
    cout << "\nTesting clear(): ";
    mBag3.clear();
    cout << mBag3 << endl;
    
    cout << "\nTesting contains(3): " << mBag2.contains(3) << endl;
    mBag2.contains(3);
    cout << mBag2 << endl;
    cout << "\nTesting contains(7): " << mBag2.contains(7) << endl;
    mBag2.contains(7);
    cout << mBag2 << endl;
    
    Bag<int> mBag4;
    mBag4.add(1);
    mBag4.add(2);
    mBag4.add(3);
    mBag4.add(4);
    mBag4.add(2);
    cout << "\nTesting getFrequency(2): " << mBag4.getFrequency(2) << endl;
    cout << "Bag contains: " << mBag4 << endl;
    
    Bag<int> test;
    cout << "\nTesting == operator test==bag4: " << (test == mBag4) << endl;
    cout << "Testing != operator test!=bag4: " << (test != mBag4) << endl;
    
    test += 1;
    cout << "Testing +=: contents " <<  test << endl;
    test += 33;
    cout << "Contents: " << test <<endl;
    
    cout << "Testing -=: contents " <<  test << endl;
    test -= 33;
    cout << "Contents: " << test <<endl;
    
    return 0;
}