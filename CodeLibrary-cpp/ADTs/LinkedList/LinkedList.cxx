/**
 * LinkedList Implementation
 * @file LinkedList.cxx
 * @author Kyle Givler
 */

/*
 * TODO: exceptions
 * TODO: additional operators
 * TODO: Iterators
 * TODO: Doubly linked list?
 */

/*************************** CONSTRUCTORS **************************/

template <class T>
LinkedList<T>::LinkedList()
{
    numberOfNodes = 0;
    head = nullptr;
    tail = nullptr;
}

template <class T>
LinkedList<T>::LinkedList(const LinkedList &original)
{
    numberOfNodes = original.numberOfNodes;
    head = nullptr;
    tail = nullptr;
    copy(original);
}

template <class T>
LinkedList<T>::~LinkedList()
{
    removeAll();
}

/*******************************************************************/

template <class T>
LinkedList<T>& LinkedList<T>::operator=(const LinkedList &rhs)
{
    if (this != &rhs)
	copy(rhs);

    return (*this);
}

/*******************************************************************/

template <class T>
void LinkedList<T>::print(std::ostream &out, char del) const
{
   node<T> *current = head;
    if (head == nullptr)
	out << "(empty)";
    else
    {
	current = head;
	while(current != nullptr)
	{
	    out << current->data;
	    if(current->next != nullptr)
		out << del;
	    current = current->next;
	}
    }
}

/*******************************************************************/

template <class T>
bool LinkedList<T>::isEmpty() const
{
    return (numberOfNodes == 0);
}

template <class T>
size_t LinkedList<T>::numberOfItems() const
{
    return numberOfNodes;
}

template <class T>
void LinkedList<T>::insertFront(T item)
{
    node<T> *oldHead = head;
    head = new node<T>(item, oldHead);
    numberOfNodes++;
}

template <class T>
void LinkedList<T>::insertEnd(T item)
{
    node<T> *current = head;
    if (head == nullptr)
	insertFront(item);
    else
    {
	while(current->next != nullptr)
	    current = current->next;
	
	current->next = new node<T>(item, nullptr);
    }
    numberOfNodes++;
}

template <class T>
void LinkedList<T>::insertByPosition(size_t index, T item)
{
    node<T> *current;
    node<T> *newNode;
    
    if( (index >=0) && (index <= numberOfNodes) )
    {
	if(index == 0)
	    insertFront(item);
	else if(index == numberOfNodes)
	    insertEnd(item);
	else
	{
	    current = head;
	    for(size_t i = 1; i < index; i++)
	    {
		current = current->next;
	    }
	    if(current == nullptr)
		insertEnd(item);
	    else
	    {
		newNode = new node<T>(item, current->next->next);
		current->next = newNode;
		numberOfNodes++;
	    }
	}
    }
}

template <class T>
void LinkedList<T>::insertByValue(T anItem)
{
    insertFront(anItem);
    node<T> *current = head;
    
    while(current->next != nullptr)
    {
	if(current->data <= current->next->data)
	    return;
	swapDataItem(current->data, current->next->data);
	current = current->next;
    }
}

template <class T>
void LinkedList<T>::swapDataItem(T& x, T& y)
{
  T temp;
  temp = x;
  x = y;
  y = temp;
}

template <class T>
void LinkedList<T>::getFront(T &item) const
{
    if(head != nullptr)
    {
	item = head->data;
    }
}

template <class T>
void LinkedList<T>::getEnd(T &item) const
{
    node<T> *current = head;
    if (head != nullptr)
    {
	while(current->next != nullptr)
	    current = current->next;
    }
    item = current->data;
}

template <class T>
void LinkedList<T>::getByPosition(size_t index, T &item) const
{
    if (index < numberOfNodes)
    {
	node<T> *current = head;
	for (size_t i = 0; i < index; i++)
	    current = current->next;
	
	item = current->data;
    }
}

template <class T>
void LinkedList<T>::removeFront()
{
    node<T> *deleteNode = head;
    if(head != nullptr)
    {
	head = head->next;
	delete deleteNode;
    }
    numberOfNodes--;
}

template <class T>
void LinkedList<T>::removeEnd()
{
    node<T> *current = head;
    node<T> *previous= nullptr;
    node<T> *deleteNode;
    
    if(head != nullptr)
    {
	while(current->next != nullptr)
	{
	    previous = current;
	    current = current->next;
	}
	if(current == head)
	{
	    deleteNode = head;
	    head = nullptr;
	}
	else
	{
	    previous->next = nullptr;
	    deleteNode = current;
	}
	delete deleteNode;
	numberOfNodes--;
    }
}


template <class T>
void LinkedList<T>::removeByPosition(size_t position)
{
    node<T> *current;
    node<T> *deleteNode;
    
    if( (position >=0) && (position < numberOfNodes) )
    {
	if(position == 0)
	    removeFront();
	else if(position == numberOfNodes)
	    removeEnd();
	else
	{
	    current = head;
	    for(size_t i = 1; i < position; i++)
		current = current->next;
	    deleteNode = current->next;
	    current->next = deleteNode->next;
	    delete deleteNode;
	}
    }
}


template <class T>
bool LinkedList<T>::removeByValue(T item)
{
    node<T> *current = head;
    for(size_t i = 0; current != nullptr; i++)
    {
	if(current->data == item)
	{
	    removeByPosition(i);
	    return true;
	}
	current = current->next;
    }
    
    return false;
}

template <class T>
void LinkedList<T>::removeAll()
{
    while(!isEmpty())
	removeFront();
}

/*******************************************************************/

template <class T>
void LinkedList<T>::copy(const LinkedList<T> &org)
{
    removeAll();
    head = nullptr;
    tail = nullptr;

    node<T> *current = org.head;
    while(current != nullptr)
    {
	insertEnd(current->data);
	current = current->next;
    }
}

/*******************************************************************/

template <class T>
std::ostream& operator<<(std::ostream &out, const LinkedList<T> &list)
{
    list.print(out, ' ');
    return out;
}

template <class T>
std::istream& operator>>(std::istream &in, LinkedList<T> &list)
{
    // TODO test
    T newDataItem;
    in >> newDataItem;
    list.insertEnd(newDataItem);
    return in;
}