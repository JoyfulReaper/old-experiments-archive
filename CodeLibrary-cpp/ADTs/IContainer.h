/**
 * Interface for container data structures
 * @file IContainer.h
 * @author Kyle Givler
 */

#ifndef _ICONTAINER_H_
#define _ICONTAINER_H_

template <class T>
class IContainer
{
public:
    virtual ~IContainer() {}
    
    /** @return True if list is empty; false otherwise */
    virtual bool isEmpty() const = 0;
    
    /** @return The number of items in the container */
    virtual size_t numberOfItems() const = 0;
    
    /** 
     * Inserts an item into the front of the container
     * @pre none
     * @param item The item to insert into the container 
     */
    virtual void insertFront(T item) = 0;
    
    /** 
     * Inserts an item into the back of the container
     * @pre none
     * @param item The item to insert into the container 
     */
    virtual void insertEnd(T item) = 0;
    
    /** 
     * Inserts an item into the container at the specified position
     * @pre none
     * @param index The index in the container at which to insert the item
     * @param item The item to insert into the container 
     */
    virtual void insertByPosition(size_t index, T item) = 0;
    
    virtual void insertByValue(T anItem) = 0;
    
    /**
     * Gets the item at the front of the container
     * @pre The container is not empty
     * @post item contains the item from the front of the container
     * @param item Will hold the item from the front of the container
     */
    virtual void getFront(T &item) const = 0;
    
    /**
     * Gets the item at the back of the container
     * @pre The container is not empty
     * @post item contains the item from the back of the container
     * @param item Will hold the item from the front of the container
     */
    virtual void getEnd(T &item) const = 0;
    
    /**
     * Gets the item at the specified index
     * @pre The container is not empty and index is a valid index
     * @post item contains the item from the specified index of the container
     * @param item Will hold the item from the front of the container
     */
    virtual void getByPosition(size_t index, T &item) const = 0;
    
    /*********************************************************************
     * Removes the item at the front of the container
     * @pre The container is not empty
     * @post The item at the front of the container has been removed
     */
    virtual void removeFront() = 0;
    
    /**
     * Removes the item at the back of the container
     * @pre The container is not empty
     * @post The item at the back of the container has been removed
     */
    virtual void removeEnd() = 0;
    
    /**
     * Removes the item at the specified index of the container
     * @pre The container is not empty and index is a valid index
     * @post The item at the specified index of the container has been removed
     */
    virtual void removeByPosition(size_t position) = 0;
    
    /**
     * @param item The item to remove from the container
     * @return true if the item was removed; false otherwise
     */
    virtual bool removeByValue(T item) = 0;
    
    /**
     * Removes all of the items from the container
     * @pre none
     * @post The container is empty
     */
    virtual void removeAll() = 0;
};

#endif
