/**
 * @file bag.h
 * @author Kyle Givler
 * 
 * A Dynmaic Array Based Bag ADT
 */

#ifndef _BAG_H__
#define _BAG_H__

#include <algorithm>
#include <iostream>

#include "IBag.h"
#include "../DynamicArray/DynamicArray.h"
#include "BagIterator.h"

template<class T>
class Bag : public BagInterface<T>
{
public:
    /**
     * Constuctor for Bag
     * @param init_size The initial size of the underlying dynamic array
     */
    Bag(size_t init_size = 10); //Initial size for array
    
    /**
     * Copy Constructor
     * @param the bag to be copied
     */
    Bag(const Bag<T> &org);
    
    /**
     * Destructor
     */
    ~Bag();
    
    // Operators
    Bag<T>& operator=(const Bag<T> &rhs);
    bool operator==(const Bag<T> &other) const;
    bool operator!=(const Bag<T> &other) const;
    Bag<T>& operator+=(const T &rhs);
    Bag<T>& operator-=(const T &rhs);
    
    // Iterators
    BagIterator<T> begin()
    { return BagIterator<T>(this, 0); }
    
    BagIterator<T> end()
    { return BagIterator<T>(this, data->eltsInUse); }
    
     /**
     * Get the number of items in the bag
     * @return number of items in the bag
     */
     size_t getSize() const;
    
     /**
     * Determine if bag is empty
     * @return true if bag is empty, false otherwise
     */
    bool isEmpty() const;
    
     /**
     *  Add an item to the bag
     * @param item Item to be insert into bag
     * @return true on sucess, false on failure
     */
    bool add(const T& item);
    
     /**
     * Remove an item from the bag
     * @param item item to remove from bag;
     * @return true on sucess, false otherwise
     */
    bool remove(const T& item);
    
     /**
     * Empty the bag
     * @post Bag contains no items
     */
    void clear();
    
     /**
     * Check to see if the bag contains an item
     * @param item The object to check for
     * @return true if the bag contains it, false otherwise
     */
    bool contains(const T& item) const;
    
     /**
     * @param item item to be counted
     * @return The number of occurences
     */
    size_t getFrequency(const T& item) const;
    
     /**
     * Convert bag to vector 
     * @return Vector containing all entries in bag
     */
    std::vector<T> toVector() const;
    
    void print(std::ostream &out, char delim = ' ') const;
    void print() const { print(std::cout, ' '); }
    
private:
   size_t itemCount; // Number of items in bag
   DynamicArray<T> *data = nullptr;
};

template <class T>
std::istream &operator>>(std::istream &inStream,Bag<T> &bag);

template <class T>
std::ostream &operator<<(std::ostream &out, const Bag<T> &bag);

#include "bag.cxx"
#endif