/**
 * LinkedList
 * @file LinkedList.h
 * @author Kyle Givler
 */

#ifndef _LINKEDLIST__H_
#define _LINKEDLIST__H_

#include <iostream>
#include "../IContainer.h"
#include "node.h"
#include "LinkedListIterator.h"

template<class T>
class LinkedList : public IContainer<T>
{
public:
    /**
     * Constructor
     */
    LinkedList();
    
    /**
     * Copy Constructor
     * @param org the LinkedList to be copied
     */
    LinkedList(const LinkedList<T> &org);
    
    /**
     * Destructor
     */
    ~LinkedList();
   
    // Iterators
   LinkedListIterator<T> begin()
   { return LinkedListIterator<T>(this, head);}
   
   LinkedListIterator<T> end()
   { return LinkedListIterator<T>(this, nullptr);}
    
    // Operators
    LinkedList<T>& operator=(const LinkedList &rhs);
    
    void print(std::ostream &out, char del = ' ') const;
    
    /**
     * Copy list to this object.
     * @param original The list to copy
     */
    void copy(const LinkedList<T> &orginal);
    
    void swapDataItem(T& x, T& y);
    
    /*******************************************************************/
    
    /** @return True if list is empty; false otherwise */
    bool isEmpty() const;
    
    /** @return The number of items in the container */
    size_t numberOfItems() const;
    
    /** 
     * Inserts an item into the front of the container
     * @pre none
     * @param item The item to insert into the container 
     */
     void insertFront(T item);
    
    /** 
     * Inserts an item into the back of the container
     * @pre none
     * @param item The item to insert into the container 
     */
     void insertEnd(T item);
    
    /** 
     * Inserts an item into the container at the specified position
     * @pre none
     * @param index The index in the container at which to insert the item
     * @param item The item to insert into the container 
     */
     void insertByPosition(size_t index, T item);
    
     void insertByValue(T anItem);
    
    /**
     * Gets the item at the front of the container
     * @pre The container is not empty
     * @post item contains the item from the front of the container
     * @param item Will hold the item from the front of the container
     */
     void getFront(T &item) const;
    
    /**
     * Gets the item at the back of the container
     * @pre The container is not empty
     * @post item contains the item from the back of the container
     * @param item Will hold the item from the front of the container
     */
     void getEnd(T &item) const;
    
    /**
     * Gets the item at the specified index
     * @pre The container is not empty and index is a valid index
     * @post item contains the item from the specified index of the container
     * @param item Will hold the item from the front of the container
     */
     void getByPosition(size_t index, T &item) const;
    
    /**
     * Removes the item at the front of the container
     * @pre The container is not empty
     * @post The item at the front of the container has been removed
     */
     void removeFront();
    
    /**
     * Removes the item at the back of the container
     * @pre The container is not empty
     * @post The item at the back of the container has been removed
     */
     void removeEnd();
    
    /**
     * Removes the item at the specified index of the container
     * @pre The container is not empty and index is a valid index
     * @post The item at the specified index of the container has been removed
     */
     void removeByPosition(size_t position);
    
    /**
     * @param item The item to remove from the container
     * @return true if the item was removed; false otherwise
     */
     bool removeByValue(T item);
    
    /**
     * Removes all of the items from the convirtualtainer
     * @pre none
     * @post The container is empty
     */
     void removeAll();
     
private:
    node<T> *head;
    node<T> *tail;
    size_t numberOfNodes;
};

template <class T>
std::ostream& operator<<(std::ostream &outStream, const LinkedList<T> &list);

template <class T>
std::istream& operator>>(std::istream &inStream, LinkedList<T> &list);

#include "LinkedList.cxx"
#endif