/**
 * Interface for stack ADT
 * @file IStack.h
 * @author Kyle Giver
 */

#ifndef _I_STACK_H_
#define _I_STACK_H_

template <class T>
class StackInterface
{
public:
    virtual ~StackInterface() {};
    /**
     * Check if the stack is isEmpty
     * @return true if empty, otherwise false
     */
    virtual bool isEmpty() const = 0;
    
    /**
     * Add new entry at top of stack
     * @param data The object to be added
     * @return true in success, false on failure
     */
    virtual bool push(const T& data) = 0;
    
    /**
     * Remove the item on the top of the stack
     * @return true on success, false on failure
     */
    virtual bool pop() = 0;
    
    /**
     * Returns the object on the top of the stack
     * @return The top of the stack
     */
    virtual T peek() const = 0;
};

#endif