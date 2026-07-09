/**
 * Iterator for the dynamic array
 * @file DynamicArrayIterator.h
 * @author Kyle Givler
 */

#ifndef _DYNAMIC_ARRAY_ITERATOR_H_
#define _DYNAMIC_ARRAY_ITERATOR_H_

#include <iterator>

template <class T>
class DynamicArray;

template <class T>
class DynamicArrayIterator : public std::iterator<std::input_iterator_tag, int>
{
private:
    const DynamicArray<T> *containerPtr;
    size_t index;
    
public:
    DynamicArrayIterator(DynamicArray<T> *array, int index) : containerPtr(array), index(index) {}
    DynamicArrayIterator(const DynamicArrayIterator<T> &dit) : containerPtr(dit.containerPtr), index(dit.index) {}
    
    DynamicArrayIterator<T>& operator++() 
    {
	index++;
	return *this;
    }
    DynamicArrayIterator<T> operator++(int) 
    {
	DynamicArrayIterator tmp(*this);
	operator++();
	return tmp;
    }
    
    bool operator==(const DynamicArrayIterator<T> &rhs)
    {
	return ( (containerPtr == rhs.containerPtr) && (index == rhs.index) );
    }
    bool operator!=(const DynamicArrayIterator<T> &rhs) {return (!operator==(rhs)) ;}
    
    const T operator*() 
    {
	T item;
	containerPtr->getByPosition(index, item);
	return ( item );
    }

};

#endif