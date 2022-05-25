#include <iostream>
#include <vector>
#include <stdlib.h>
#include"BigDecimalInt.h"
using namespace std;
void Menu()
{
    cout << "Welcome to FCI Calculator" << endl;
    cout << "1- Addition" << endl;
    cout << "2- Subtraction" << endl;
    cout << "3- Exit" << endl;
}
int main()
{
    int choice;
    string str;
    while(true)
    {
        Menu();
        cin >> choice;
        if(choice==1)
        {
            cout<<"enter first number"<<endl;
            cin >> str;
            BigDecimalInt myBig1(str);
            cout<<"enter second number"<<endl;
            cin >> str;
            BigDecimalInt myBig2(str);
            BigDecimalInt res;
            if(myBig1.Negative())
                res = myBig2 - myBig1;
            else if(myBig2.Negative())
                res = myBig1 - myBig2;
            else
                res = myBig1 + myBig2;
            cout << "number1+number2 = " << res << endl;
        }

        else if(choice==2)
        {
            cout<<"enter first number "<<endl;
            cin >> str;
            BigDecimalInt myBig1(str);
            cout<<"enter second number"<<endl;
            cin >> str;
            BigDecimalInt myBig2(str);
            BigDecimalInt res;
            if(myBig1.Negative() || myBig2.Negative())
            {
                res = myBig1 + myBig2;
                res.setStatus((myBig1.Negative()) ? 1:0);
            }
            else
                res = myBig1 - myBig2;
            cout << "number1+number2 = " << res << endl;
        }
        else
        {
            cout<<"Thank you for using our calculator"<<endl;
            break;
        }

    }
    return 0;
}

