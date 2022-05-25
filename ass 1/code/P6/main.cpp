#include <iostream>
#include "problem6.h"
//#include "problem1.h"



int main()
{

    cout << "hello world" << endl << endl;






















    cout << "test 1" << endl;
   StudentName test1("maher esmat sohsah");
    if(test1.Replace(1,4))
    {
        cout << "True" << endl;
        test1.print();
        cout <<endl;
    }
    else
    {
        cout << "False" << endl;
        test1.print();
        cout << endl;
    }

    cout << "test 2" << endl;
    StudentName test2("mohamed");
    if(test2.Replace(2,3))
    {
        cout << "True" << endl;
        test2.print();
        cout <<endl;
    }
    else
    {
        cout << "False" << endl;
        test2.print();
        cout << endl;
    }

    cout << "test 3" << endl;
    StudentName test3("Fatma Ashraf");
    if(test3.Replace(1,8))
    {
        cout << "True" << endl;
        test3.print();
        cout <<endl;
    }
    else
    {
        cout << "False" << endl;
        test3.print();
        cout << endl;
    }

    cout << "test 4" << endl;
    StudentName test4("omar khaled karim mohamed mohamed");
    if(test4.Replace(1,5))
    {
        cout << "True" << endl;
        test4.print();
        cout <<endl;
    }
    else
    {
        cout << "False" << endl;
        test4.print();
        cout << endl;
    }

    cout << "test 5" << endl;
    StudentName test5("Ibrahim ramadan");
    if(test5.Replace(1,2))
    {
        cout << "True" << endl;
        test5.print();
        cout <<endl;
    }
    else
    {
        cout << "False" << endl;
        test5.print();
        cout << endl;
    }







    return 0;
}
