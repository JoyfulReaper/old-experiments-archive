/**
 * Node
 * @file node.h
 * @author Kyle Givler
 */

#ifndef _NODE_H__
#define _NODE_H__

template<class T>
class node
{
public:
    T data;
    node *next;
    node(T i, node *l): data(i), next(l)
    {
    };
};

#endif