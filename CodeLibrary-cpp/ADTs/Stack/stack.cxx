/**
 * Stack ADT
 * @file stack.cxx
 * @author Kyle Giver
 */


template<class T>
Stack<T>::Stack(size_t size)
{
    numItems = 0;
    data = new DynamicArray<T>(size);
}

template<class T>
Stack<T>::Stack(const Stack &org)
{
    numItems = org.numItems;
    data = new DynamicArray<T>(numItems * 1.5);
    T item;
    for(size_t i = 0; i < numItems; i++)
    {
	org.data->getByPosition(i, item);
	data->insertByPosition(i, item);
    }
}

template<class T>
Stack<T>::~Stack()
{
    data->removeAll();
    delete data;
}

template<class T>
Stack<T>& Stack<T>::operator=(const Stack<T> &rhs)
{
    if(this != &rhs)
    {
	numItems = rhs.numItems;
	if(data != nullptr)
	    delete data;
	data = new DynamicArray<T>(numItems * 1.5);
	T item;
	for(size_t i = 0; i < numItems; i++)
	{
	    rhs.data->getByPosition(i, item);
	    data->insertByPosition(i, item);
	}
    }
    return (*this);
}

template<class T>
bool Stack<T>::operator==(const Stack<T> &rhs) const
{
    if(numItems != rhs.numItems)
	return false;
    
    auto oit = rhs.data->begin();
    for(auto it = data->begin(); it != data->end(); ++it)
    {
	if(*it != *oit)
	    return false;
	++oit;
    }
    return true;
}

template<class T>
bool Stack<T>::operator!=(const Stack<T> &rhs) const
{
    return !(*this == rhs);
}

template <class T>
bool Stack<T>::isEmpty() const
{
    return (numItems == 0);
}

template <class T>
bool Stack<T>::push(const T& item)
{
    data->insertEnd(item);
    numItems++;
    return true;
}

template <class T>
bool Stack<T>::pop()
{
    if (numItems == 0)
	return false;
    
    data->removeEnd();
    numItems--;
    return true;
}

template <class T>
T Stack<T>::peek() const
{
    T item;
    data->getByPosition(numItems - 1, item);
    return item;
}

template <class T>
void Stack<T>::print(std::ostream &out, char del) const
{
    T item;
    for(size_t i = 0; i < numItems; i++)
    {
	data->getByPosition(i, item);
	out << item;
	if (i < (numItems -1))
	    out << del;
	else
	    out << std::endl;
    }
}

template <class T>
std::ostream &operator<<(std::ostream &out, const Stack<T> &rhs)
{
    rhs.print(out, ' ');
    return out;
}