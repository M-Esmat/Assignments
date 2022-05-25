#include <iostream>

using namespace std;


template <class type>

class Queue
{
    protected:

    public:
        struct Node
        {
            type data;
            Node* next;
        };

        Node* qfront;
        Node* qend;
        int length = -1;
        Queue()
        {

            qfront = NULL;
            qend = NULL;
            length++;
        }

        Queue(type value, int intial_size)
        {
            qfront = NULL;
            qend = NULL;
            length++;
            while (intial_size > 0)
            {
                push(value);
                intial_size--;

            }


        }


        bool isempty()
        {
            return (size() == 0);
        }

        void push(type value)
        {
            Node* item = new Node();
            item -> data = value;
            item -> next = NULL;

            if (qfront == NULL)
                qfront = qend = item;
            else
            {
                qend -> next = item;
                qend = item;
            }
            length++;
            cout << "value entered is: " << value << endl;
            cout << "queue length is: " << size() << endl;
        }

        void pop()
        {
            if (isempty())
            {
                cout << "queue is empty" << endl;
            }

            else
            {
                Node* temp = qfront;
                qfront = qfront -> next;
                delete temp;
                length--;

            }

              cout << "queue size is: " << size() << endl;
        }

        type& front()
        {
            if (isempty())
                cout << "it's empty" << endl;
            else
                return qfront ->data;
        }

        int size()
        {
            return length;
        }

        ~Queue()
        {
            Node *temp = new Node();
            while(qfront != NULL)
            {
                temp = qfront;
                qfront = qfront -> next;
                delete temp;
            }
     }

};

class intQueue
{
    protected:
        struct Node
        {
            int data;
            Node* next;
        };

        Node* qfront;
        Node* qend;
        int length = -1;


    public:

        intQueue()
        {

            qfront = NULL;
            qend = NULL;
            length++;
        }


        bool isempty()
        {
            if (size() == 0)
            {
                qfront = qend = NULL;
            }
            return (size() == 0);
        }


        void push(int value)
        {
            Node* item = new Node();
            item -> data = value;
            item -> next = NULL;

            if (qfront == NULL)
                qfront = qend = item;
            else
            {
                item -> next = qfront;
                qfront = item;
            }
            length++;
            cout << "value entered is: " << value << endl;
            cout << "queue length is: " << size() << endl;
        }


        void pop()  // fe error lw el queue fadya
        {
            if (isempty())
            {
                cout << "queue is empty" << endl;
            }

            else
            {
                Node* temp = qfront;
                qfront = qfront -> next;
                delete temp;
                length--;

            }

              cout << "queue size is: " << size() << endl;
        }

        int top()
        {
            if (isempty())
                cout << "it's empty" << endl;
            else
                return qfront ->data;
        }

        int size()
        {
            return length;
        }

        ~intQueue()
        {
            Node *temp = new Node();
            while(qfront != NULL)
            {
                temp = qfront;
                qfront = qfront -> next;
                delete temp;
            }
     }

};


int main()
{

    Queue<int> q;
    q.push(10);
    q.push(20);
    q.push(30);
    q.push(50);
    q.pop();
    q.pop();
    q.push(100);
    q.pop();
    q.pop();
    q.pop();
    q.pop();
    q.pop();
    q.pop();
    /*
    cout << "--------------------------------------------------" << endl;
    cout << "trying second constructor"<< endl;
    Queue<int> q2(5, 6);
    q2.pop();
    q2.pop();
    q2.push(3);
    q2.push(6);
*/
/*
    intQueue intq1;
    intq1.push(10);
    intq1.push(20);
    intq1.push(30);
    intq1.push(40);
    intq1.push(50);
cout << "--------------- pop --------------" << endl;
    intq1.pop();
    intq1.pop();
    intq1.pop();
    intq1.pop();
    intq1.pop();
    intq1.pop();

*/




    return 0;
};
