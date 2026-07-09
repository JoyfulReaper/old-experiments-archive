/**
 * @file bag.cxx
 * @author Kyle Givler
 * 
 * A Dynmaic Array Based Bag ADT
 */

/*************************** CONSTRUCTORS **************************/

template <class T>
Bag<T>::Bag(size_t init_size)
{
    itemCount = 0;
    data = new DynamicArray<T>(init_size);
}

template <class T>
Bag<T>::Bag(const Bag<T> &org)
{
    itemCount = org.itemCount;
    
    if(org.data != nullptr)
    {
	data = new DynamicArray<T>(itemCount);
    
	for(size_t i = 0; i < itemCount; i++)
	{
	    T item;
	    org.data->getByPosition(i, item);
	    data->insertEnd(item);
	}
    }
    else
	data = nullptr;
}

template <class T>
Bag<T>::~Bag()
{
    delete data;
}

/************************** OPERATORS ******************************/

template <class T>
Bag<T>& Bag<T>::operator=(const Bag<T> &rhs)
{
    if(this != &rhs)
    {
	itemCount = rhs.itemCount;
	if (data != nullptr)
	    delete data;
	
	data = new DynamicArray<T>(rhs.data->numberOfItems());
	
	for(size_t i = 0; i < itemCount; i++)
	{
	    T item;
	    rhs.data->getByPosition(i, item);
	    data->insertEnd(item);
	}
    }
    return (*this);
}

template <class T>
bool Bag<T>::operator==(const Bag<T> &other) const
{
    if(itemCount != other.itemCount)
	return false;
    
    auto oit = other.data->begin();
    for(auto it = data->begin(); it != data->end(); ++it)
    {
	if (*it != *oit)
	    return false;
	++oit;
    }
    return true;
}

template <class T>
bool Bag<T>::operator!=(const Bag<T> &other) const
{
    return !(*this == other);
}

template <class T>
Bag<T>& Bag<T>::operator+=(const T &rhs)
{
    add(rhs);
    return *this;
}

template <class T>
Bag<T>& Bag<T>::operator-=(const T &rhs)
{
    remove(rhs);
    return *this;
}
/*********************************************************************/

template <class T>
inline size_t Bag<T>::getSize() const
{
    return itemCount;
}

template <class T>
inline bool Bag<T>::isEmpty() const
{
    return (itemCount == 0);
}

template <class T>
bool Bag<T>::add(const T& item)
{
    data->insertEnd(item);
    itemCount++;
    
    return true;
}

template <class T>
bool Bag<T>::remove(const T& item)
{
    bool ret = data->removeByValue(item);
    if(ret)
	itemCount--;
    
    return ret;
}

template <class T>
void Bag<T>::clear()
{
    data->removeAll();
    itemCount = 0;
}

template <class T>
bool Bag<T>::contains(const T& item) const
{
    for(auto it = data->begin(); it != data->end(); ++it)
	if (*it == item)
	    return true;
    
    return false;
}

template <class T>
size_t Bag<T>::getFrequency(const T& item) const
{
    size_t count = 0;
    
    for(auto it = data->begin(); it != data->end(); ++it)
	if (*it == item)
	    count++;
	
    return count;
}

template <class T>
std::vector<T> Bag<T>::toVector() const
{
    // TODO: Test
    std::vector<T> bagVector;
    for(auto it = data->begin(); it != data->end(); ++it)
	bagVector.push_back(*it);
 
    return bagVector;
}

/*********************************************************************/

template <class T>
void Bag<T>::print(std::ostream &out, char delim) const
{
    data->print(out, delim);
}

template <class T>
std::istream &operator>>(std::istream &inStream,Bag<T> &bag)
{
    // TODO: Test
    T newDataItem;
    inStream >> newDataItem;
    bag.add(newDataItem);
    return inStream;
}

template <class T>
std::ostream &operator<<(std::ostream &out, const Bag<T> &bag)
{
    bag.print(out, ' ');
    return out;
}
