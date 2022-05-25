#include <iostream>

using namespace std;

template<class t>
class Stack
{
private:
    struct Node
    {
        t item;
        Node* next;
    };
    Node* top, * cur;
    int length;

public:

    Stack()
    {
        top = NULL;
        length = 1;
    }

    void push(t element)
    {
        Node* item = new Node;
        if (item == NULL)
            cout << "cant allocate memory ....\n";
        else
        {
            item->item = element;
            item->next = top;
            top = item;
            length++;
        }
    }

    bool isEmpty()
    {
        return top == NULL;
    }
    void pop()
    {
        if (isEmpty())
            cout << "stack is empty...\n";
        else
        {

            Node* Temp = top;
            top = top->next;
            Temp = Temp->next = NULL;
            delete Temp;
            length--;
        }
    }
    t getTop()
    {
        if (isEmpty())
            cout << "stack is empty...\n";
        else
        {
            return top->item;
        }
       
    }

   

    int Size()
    {
        return length;
    }

    ~Stack()
    {
        Node* current = top;

        while (current != 0) {
            Node* next = current->next;
            delete current;
            current = next;
        }
        top = 0;
        length = 0;
    }

};
bool paired(char open, char close)
{
    if (open == '(' && close == ')')
        return true;
    else if (open == '{' && close == '}')
        return true;
    else if (open == '[' && close == ']')
        return true;
    else if (open == '/*' && close == '*/')
    return false;
}
bool AreBalanced(string exp)
{
    Stack<char>  S;
    int length = exp.length();
    for (int i = 0; i < length; i++)
    {
        if (exp[i] == '(' || exp[i] == '{' || exp[i] == '[' || exp[i] == '/*')
            S.push(exp[i]);
        else if (exp[i] == ')' || exp[i] == '}' || exp[i] == ']' || exp[i] == '*/')
        {
            if (S.isEmpty() || !paired(S.getTop(), exp[i]))
                return false;
            else
                S.pop();
        }
    }
    return S.isEmpty() ? true : false;
}



int main()
{
    string exp;
    cout << "Enter your expresion: ";
    cin >> exp;
    if (AreBalanced(exp))
        cout << "Valid" << endl;
    else
        cout << "Invalid" << endl;
    
    return 0;
}



