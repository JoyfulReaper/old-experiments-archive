/**
 * Dynamic Array Implmentation
 * @file DynamicArray.cxx
 * @author Kyle Givler
 */

/*************************** CONSTRUCTORS **************************/

template <class T>
DynamicArray<T>::DynamicArray(size_t cap)
{
    capacity = cap;
    if(capacity < 1)
	capacity = 1;

    eltsInUse = 0;
    data = new T[capacity];
}

template <class T>
DynamicArray<T>::DynamicArray(const DynamicArray<T> &original)
{
    capacity = original.capacity;
    eltsInUse = original.eltsInUse;

    if (original.data != nullptr)
    {
	data = new T[capacity];
	for(size_t i = 0; i < eltsInUse; i++)
	    data[i] = original.data[i];
    } 
    else 
	data = nullptr;
}

template <class T>
DynamicArray<T>::~DynamicArray()
{
    delete[] data;
}

/************************ OPERATORS ************************************/

template <class T>
DynamicArray<T>& DynamicArray<T>::operator=(const DynamicArray<T> &rhs)
{
    if (this != &rhs)
    {
	capacity = rhs.capacity;
	eltsInUse = rhs.eltsInUse;
	if (data != nullptr)
	    delete[] data;

	data = new T[capacity];
	if (eltsInUse != 0)
	    for (size_t i = 0; i < eltsInUse; i++)
		data[i] = rhs.data[i];
    }
	return (*this);
}

template<class T>
bool DynamicArray<T>::operator==(const DynamicArray<T> &other) const
{   
   if (numberOfItems() != other.numberOfItems())
       return false;
    
    for (size_t i = 0; i < other.numberOfItems(); i++)
	if(data[i] != other.data[i])
	    return false;
	
    return true;
}

template<class T>
bool DynamicArray<T>::operator!=(const DynamicArray<T> &other) const
{
    return !(*this == other);
}

template<class T>
DynamicArray<T>& DynamicArray<T>::operator+=(const T &rhs)
{
    // TODO: Test
    insertEnd(rhs);
}

template<class T>
DynamicArray<T>& DynamicArray<T>::operator-=(const T &rhs)
{
    // TODO: Test
    removeByValue(rhs);
}

template <class T>
T &DynamicArray<T>::operator[](size_t i) throw(std::out_of_range)
{
	if (i > eltsInUse)
		throw std::out_of_range("Invalid index");

	return data[i];
}

template <class T>
const T& DynamicArray<T>::operator[](size_t i) const throw(std::out_of_range)
{
    if (i > eltsInUse)
	throw std::out_of_range("Invalid index");

    return data[i];
}
/**********************************************************************/

template <class T>
void DynamicArray<T>::print(std::ostream &out, char delimiter) const
{
    for (size_t i = 0; i < eltsInUse; i++)
    {
	out << data[i];
	if (i < eltsInUse - 1)
	    out << delimiter;
    }
}

template <class T>
bool DynamicArray<T>::isEmpty() const
{
    return (eltsInUse == 0);
}

template <class T>
size_t DynamicArray<T>::numberOfItems()const
{
    return eltsInUse;
}

template <class T>
void DynamicArray<T>::insertFront(T item)
{
    insertByPosition(0, item);
}

template <class T>
void DynamicArray<T>::insertEnd(T item)
{
    insertByPosition(eltsInUse, item);
}

template <class T>
void DynamicArray<T>::insertByPosition(size_t index, T item) throw (std::invalid_argument)
{		
    if(eltsInUse == capacity)
	resize();

    size_t position;
    if((index>0) && (index <= eltsInUse))
    {
	for(position = eltsInUse; position >= index; position--)
	    data[position] = data[position-1];
	    data[index] = item;
	    eltsInUse++;
	    return;
    }
    else if (index >= 0 && index <= eltsInUse)
    {	  
	if(eltsInUse > 0)
	    for(position = eltsInUse; position > 0; position--)
		data[position] = data[position - 1];

	data[0] = item;
	eltsInUse++;
	return;
    }
    throw std::invalid_argument("Invalid Index: " + std::to_string(index));	
}

template <class T>	
void DynamicArray<T>::insertByValue(T item) 
{
  size_t index = eltsInUse;
  bool done = false;

  insertEnd(item);

  while( (index > 0) && (!done) )
    {
      done = data[index-1] <= data[index];
      if(!done)
	{
	  swap(index-1, index);
	}
	    index--;
    }
}


template <class T>
void DynamicArray<T>::getFront(T &item) const throw(std::out_of_range)
{
    if(isEmpty())
	throw std::out_of_range("Invalid index");
    item = data[0];
}

template <class T>
void DynamicArray<T>::getEnd(T &item) const throw(std::out_of_range)
{
    if(isEmpty())
	throw std::out_of_range("Invalid index");
    item = data[eltsInUse -1];
}

template <class T>
void DynamicArray<T>::getByPosition(size_t index, T &item) const throw(std::out_of_range)
{
    if(index > eltsInUse -1)
	throw std::out_of_range("Invalid index");
    
    item = data[index];
}

template <class T>
void DynamicArray<T>::removeFront() throw(std::out_of_range)
{
    if(isEmpty())
	throw std::out_of_range("Invalid index");
    
    for(size_t i = 0; i < eltsInUse; i++)
	data[i] = data[i + 1];
    eltsInUse--;
}

template <class T>
void DynamicArray<T>::removeEnd() throw(std::out_of_range)
{
    if(isEmpty())
	throw std::out_of_range("Invalid index");
    
    eltsInUse--;
}

template <class T>
void DynamicArray<T>::removeByPosition(size_t index) throw(std::invalid_argument)
{
  size_t position;

  if((index > 0) && (index < eltsInUse) )
    {
      for(position = index; position < eltsInUse; position++)
	data[position] = data[position + 1];
    }
   else
       throw std::out_of_range("Invalid index");
   
  eltsInUse--;
}

template <class T>
bool DynamicArray<T>::removeByValue(T item)
{
  size_t index;
  size_t position = 0;
  bool found = false;

  while (position < eltsInUse && !found)
    {
      found = data[position] == item;
      if(!found)
	position++;
    }

  if(found)
    {
      for(index = position; index < eltsInUse; index++)
	data[index] = data[index + 1];

      eltsInUse--;
    }
    return found;
}

template <class T>
void DynamicArray<T>::removeAll() 
{
    eltsInUse = 0;
}

template <class T>
void DynamicArray<T>::swap(size_t index1, size_t index2) throw(std::out_of_range)
{
    if((index1 > eltsInUse -1) || (index2 > eltsInUse -1))
	throw std::out_of_range("Invalid index");
    
    T temp = data[index1];
    data[index1] = data[index2];
    data[index2] = temp;
}

template <class T>
void DynamicArray<T>::resize()
{
    capacity = capacity * 1.5;

    T *newData = new T[capacity];
    for(size_t i = 0; i < eltsInUse; i++)
    newData[i] = data[i];

    delete[] data;
    data = newData;
}

/************************ NONMEMBER METHODS ************************************/

template <class T>
std::ostream &operator<<(std::ostream &outStream, const DynamicArray<T> &arr)
{
  arr.print(outStream, ' ');
  return outStream;
}

template <class T>
std::istream &operator>>(std::istream &inStream, DynamicArray<T> &arr)
{
  T newDataItem;
  inStream >> newDataItem;
  arr.insertEnd(newDataItem);

  return inStream;
}
