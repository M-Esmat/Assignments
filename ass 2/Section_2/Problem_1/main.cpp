#include <iostream>
#include "Node.h"
#include "List.h"
using namespace std;

// Doubly linked list link node with freelist support


//Given two sorted linked lists, merge them in the first one without using an extra list, and without keeping any duplicates.
void MergeLists(myList<int>& list1, myList<int>& list2) {
    auto FirstIterator = list1.begin();
    auto SecondIterator = list2.begin();


    while (FirstIterator != list1.end() && list2.size() > 0) {
        if (*SecondIterator == *FirstIterator)
            list2.erase(SecondIterator);

        else if (*FirstIterator > * SecondIterator) {
            list1.insert(*SecondIterator, FirstIterator);
            list2.erase(SecondIterator);
            FirstIterator++;
        }
        else if (*FirstIterator < *SecondIterator) {
            FirstIterator++;
        }
    }
    while (list2.size() > 0) {
        list1.insert(*SecondIterator, FirstIterator);
        list2.erase(SecondIterator);
        FirstIterator++;
    }
}
int main()
{
    try {
        myList<int> list1;//create the list
        myList<int>::iterator FirstIterator = list1.begin();
        int i = 0;
        list1.insert(88, FirstIterator);
        list1.insert(75, FirstIterator);
        list1.insert(69, FirstIterator);
        list1.insert(47, FirstIterator);
        list1.insert(31, FirstIterator);
        list1.insert(28, FirstIterator);
        list1.insert(9, FirstIterator);
        list1.insert(0, FirstIterator);
        //print list one
        cout << "First Linked List: " << endl;
        for (auto iter = list1.begin(); i < list1.size(); iter++, i++) {
            cout << *iter << " ";
        }
        cout << endl;


        myList<int> list2;//create Another list
        myList<int>::iterator SecondIterator = list2.begin();
        int j = 0;

        list2.insert(73, SecondIterator);
        list2.insert(68, SecondIterator);
        list2.insert(66, SecondIterator);
        list2.insert(47, SecondIterator);
        list2.insert(27, SecondIterator);
        list2.insert(25, SecondIterator);
        list2.insert(-6, SecondIterator);
        list2.insert(-13, SecondIterator);
        //print list two
        cout << "\nSecond Linked List: " << endl;
        for (auto iter = list2.begin(); j < list2.size(); iter++, j++) {
            cout << *iter << " ";
        }
        cout << endl;

        MergeLists(list1, list2);

        cout << "\nFirst Linked List After Merging" << endl;
        int  A= 0;
        for (auto iter = list1.begin(); A < list1.size()-1; iter++, A++)
            cout << *iter << " ";
        cout << endl;
    }
    catch (const char* exption) {
        cout << exption << endl;
    }
    return 0;
}
