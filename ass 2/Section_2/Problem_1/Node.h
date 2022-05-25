#ifndef NODE_H_INCLUDED
#define NODE_H_INCLUDED

template <typename T>
class theNode
{
public:
    T element; // Value for this node
    theNode* next; // Pointer to next node in list
    theNode* prev; // Pointer to previous node

    // Constructor
    theNode(const T& item, theNode* prevPointer, theNode* nextPointer)
    {
        element = item;
        prev = prevPointer;
        next = nextPointer;
    }
    theNode(theNode* prevPointer = NULL, theNode* nextPointer = NULL)
    {
        prev = prevPointer;
        next = nextPointer;
    }
};


#endif // NODE_H_INCLUDED
