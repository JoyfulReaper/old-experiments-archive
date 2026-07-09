/**
 * Iterator for the Bag ADT
 * @file BagIterator.h
 * @author Kyle Givler
 */

#ifndef _BAG_ITERATOR_H_
#define _BAG_ITERATOR_H_

#include <iterator>

template <class T>
class Bag;

template <class T>
class BagIterator : public std::iterator<std::input_iterator_tag, int>
{
private:
    const DynamicArray<T> *containerPtr;
    size_t index;
    
public:
    BagIterator(DynamicArray<T> *array, int index) : containerPtr(array), index(index) {}
    BagIterator(const BagIterator<T> &dit) : containerPtr(dit.containerPtr), index(dit.index) {}
    
    BagIterator<T>& operator++() 
    {
	index++;
	return *this;
    }
    BagIterator<T> operator++(int) 
    {
	BagIterator tmp(*this);
	operator++();
	return tmp;
    }
    
    bool operator==(const BagIterator<T> &rhs)
    {
	return ( (containerPtr == rhs.containerPtr) && (index == rhs.index) );
    }
    bool operator!=(const BagIterator<T> &rhs) {return (!operator==(rhs)) ;}
    
    const T operator*() 
    {
	T item;
	containerPtr->getByPosition(index, item);
	return ( item );
    }

};

#endif