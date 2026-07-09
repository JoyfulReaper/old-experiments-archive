/**
 * Test program for LinkedList ADT
 * @file test.cpp
 * @author Kyle Givler
 */

#include <iostream>
#include "LinkedList.h"

using namespace std;

int main()
{
    LinkedList<int> list;
    LinkedList<int> list2;
    
    cout << "isEmpty(): " << list.isEmpty() << endl;
    cout << "numberOfItems(): " << list.numberOfItems() << endl;
    cout << "Testing insertFront()\n";
    list.insertFront(3);
    list.insertFront(2);
    list.insertFront(1);
    cout << "isEmpty(): " << list.isEmpty() << endl;
    cout << "numberOfItems(): " << list.numberOfItems() << endl;
    
    cout << "The list contains: " << list << endl;
    cout << "Inserting at end: " << endl;
    list.insertEnd(4);
    list.insertEnd(5);
    list.insertEnd(6);
    cout << "The list contains: " << list << endl;
    
    cout << "\nTesting insertByPosition(0,0): \n";
    list.insertByPosition(0,0);
    cout << "The list contains: " << list << endl;
    cout << "\nTesting insertByPosition(7,77): \n";
    list.insertByPosition(7,77);
    cout << "The list contains: " << list << endl;
    cout << "\nTesting insertByPosition(2,22): \n";
    list.insertByPosition(2,22);
    cout << "The list contains: " << list << endl;
    
    cout << "\nTesting operator=(): " << endl;
    list2 = list;
    cout << "list1: " << list << endl;
    cout << "list2: " << list2 << endl;
    
    cout << "\nTesting Copy Constructor: " << endl;
    LinkedList<int> list3(list2);
    cout << "list2: " << list << endl;
    cout << "list3: " << list2 << endl;
    
    int data;
    list3.getFront(data);
    cout << "\nTesting getFront(): " << data << endl;
    list3.getEnd(data);
    cout << "Testing getEnd(): " << data << endl;
    cout << list3 << endl;
    
    cout << "Testing removeFront(): " << endl;
    list3.removeFront();
    cout << "Testing removeEnd(): " << endl;
    list3.removeEnd();
    cout << list3 << endl;
    list3.getByPosition(4, data);
    cout << "Testing getByPosition(4): " << data << endl;
    
    cout << "\nTesting RemoveByPosition(3)\n";
    list3.removeByPosition(3);
    cout << list3 <<endl;
    cout << "\nTesting RemoveByValue(22)\n";
    list3.removeByValue(22);
    cout << list3 <<endl;
    
    LinkedList<int> list4;
    cout << "\nTesting insertByValue(1,2,3,4,5) \n";
    list4.insertByValue(2);
    list4.insertByValue(4);
    list4.insertByValue(5);
    list4.insertByValue(3);
    list4.insertByValue(1);
    cout << list4 << endl;
    return 0;
}