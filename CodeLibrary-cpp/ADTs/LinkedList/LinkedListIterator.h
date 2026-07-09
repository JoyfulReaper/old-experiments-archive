/**
 * Iterator for the LinkedList
 * @file LinkedListIterator.h
 * @author Kyle Givler
 */

#ifndef _LINKED_LIST_ITERATOR_H_
#define _LINKED_LIST_ITERATOR_H_

#include <iterator>
#include "node.h"

template <class T>
class LinkedList;

template <class T>
class LinkedListIterator : public std::iterator<std::input_iterator_tag, int>
{
private:
    const LinkedList<T> *currentPtr;
    node<T> *currentItem;
    
public:
    LinkedListIterator(LinkedList<T> *list, node<T> nodePtr) : currentPtr(list), currentItem(nodePtr) {}
    LinkedListIterator(const LinkedListIterator<T> &dit) : currentPtr(dit.currentPtr), currentItem(dit.currentItem) {}
    
    LinkedListIterator<T>& operator++() 
    {
	currentItem = currentItem->next;
	return *this;
    }
    LinkedListIterator<T> operator++(int) 
    {
	LinkedListIterator tmp(*this);
	operator++();
	return tmp;
    }
    
    bool operator==(const LinkedListIterator<T> &rhs)
    {
	return ( (currentPtr == rhs.currentPtr) && (currentItem == rhs.currentItem) );
    }
    bool operator!=(const LinkedListIterator<T> &rhs) {return (!operator==(rhs)) ;}
    
    const T operator*() 
    {
	T item;
	item = currentItem->data;
	return ( item );
    }

};

#endif