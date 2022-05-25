#ifndef LIST_H_INCLUDED
#define LIST_H_INCLUDED
#include"Node.h"
#include<iostream>
using namespace std;
template <typename T>
class myList
{
private:
    theNode<T>* head; // Pointer header
    theNode<T>* tail; // Pointer tail
    int Size; // Size of list

public:
    // default constructor.
    myList()
    {
        head = new theNode<T>;
        tail = new theNode<T>;
        tail->prev = head;
        head->next = tail;
        Size = 0;
    }
    class iterator {
        friend class myList;

    private:
        theNode<T>* node;

    public:
        // default constructor.
        iterator() {
            node = NULL;
        }

        iterator(theNode<T>* n) {
            node = n;
        }
        // overload the postincrement operator ++
        void operator++(int) {
            node = node->next;
        }
        // overload  the dereference operator --
        void operator--(int) {
            node = node->prev;
        }
        // overload  the dereference operator *
        T& operator*() {
            return node->next->element;
        }
        // overload  the dereference operator ==
        bool operator == (const iterator& iter){
            if (node == iter.node)
                return true;
            return false;
        }
        // Overload the comparison operator !=
        bool operator != (const iterator& iter) {
            if (node != iter.node)
                return true;
            return false;
        }
    };

    //a destructor to clear the list and leave no memory leaks.
    ~myList()
    {
        theNode<T>* toDelete;
        while (head != NULL)
        {
            toDelete = head;
            head = head->next;
            delete toDelete;
        }
    }

    //class iterator:the inner class

    // Insert at current position
    void insert(const T& value, iterator pos)
    {
        theNode<T>* node = new theNode<T>(value, pos.node, pos.node->next);
        pos.node->next = node;
        pos.node->next->prev = node;
        Size++;
    }

    // function erase to remove the node
    T erase(iterator pos)
    {
        if (pos.node->next == tail)
            throw "This element is out of boundaries";

        T iter = pos.node->next->element;
        theNode<T>* ltemp = pos.node->next;
        pos.node->next->next->prev = pos.node;
        pos.node->next = pos.node->next->next;
        delete ltemp;
        Size--;
        return iter;
    }
    // returns the current number of elements in the list.
    int size() const
    {
        return Size;
    }
    //returns an iterator pointing to the first element.
    iterator begin() {
        return iterator(head);
    }
    // returns an iterator pointing after the last element.
    iterator end() {
        return iterator(tail);
    }
    // overload  the dereference operator =
    myList<T>& operator=(myList<T>& list) {
        iterator iter1 = list.begin();
        iterator iter2 = begin();
        int i = 0;
        while (i < list.size()) {
            insert(*iter1, iter2);
            iter1++;
            iter2++;
            i++;
        }
        return *this;
    }

};

#endif // LIST_H_INCLUDED
