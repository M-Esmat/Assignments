
#include "MatrixCalculator.h"
#include "Matrix.h"
#include <iostream>
using namespace std;
MatrixCalculator::MatrixCalculator()
{
    int choice;
    while(true)
    {
        Menu();
        cin>>choice;
        if(choice ==1 || choice ==2 || choice ==3)
        {
            int x,y;
            cout<<"Enter First Matrix's Dimensions in this order: row , cols"<<endl;
            cin>>x;
            cin>>y;
            Matrix m1(x,y);
            cout<<"Enter Matrix's Elements:"<<endl;
            cin>>m1;
            cout<<"Enter Second Matrix's Dimensions in this order: row , cols"<<endl;
            cin>>x;
            cin>>y;
            Matrix m2(x,y);
            cout<<"Enter Matrix's Elements:"<<endl;
            cin>>m2;

            if(choice==1)
            {
                addition(m1,m2);
            }
            if(choice==2)
            {
               subtraction(m1,m2);
            }
            if(choice==3)
            {
                multiplication(m1,m2);
            }
        }

        else if(choice==4)
        {
            int x,y;
            cout<<"Enter Matrix's Dimensions in this order: row , cols"<<endl;
            cin>>x;
            cin>>y;
            Matrix m1(x,y);
            cout<<"Enter Matrix's Elements:"<<endl;
            cin>>m1;
            Calc_transpose(m1);
        }
        else
            break;

    }
}
void MatrixCalculator:: Menu()
{
    cout<<"Welcome to Ahmed's Matrix Calculator"<<endl;
    cout<<"-----------------------------------------"<<endl;
    cout<<"1- Perform Matrix Addition"<<endl;
    cout<<"2- Perform Matrix Subtraction"<<endl;
    cout<<"3- Perform Matrix Multiplication"<<endl;
    cout<<"4- Matrix Transpose"<<endl;
}
void MatrixCalculator::addition(Matrix&m1,Matrix&m2)
{
    try
    {
        if ((m1.getrows()==m2.getrows())&& (m1.getcols()==m2.getcols()))
        {

            Matrix result = m1+m2;
            cout<<"Result"<<endl;
            cout<<result<<endl;
        }
        else
            throw "wrong dimension error ";
    }
    catch(const char* msg)
    {
        cout<<msg<<endl;
    }
}
void MatrixCalculator::subtraction(Matrix&m1,Matrix&m2)
{
    try
    {
        if ((m1.getrows()==m2.getrows())&& (m1.getcols()==m2.getcols()))
        {
            Matrix result = m1-m2;
            cout<<"Result"<<endl;
            cout<<result<<endl;
        }
        else
            throw "wrong dimension error ";
    }
    catch(const char* msg)
    {
        cout<<msg<<endl;
    }
}

void MatrixCalculator::multiplication(Matrix&m1,Matrix&m2)
{
    try
    {
        if (m1.getcols()==m2.getrows())
        {

            Matrix result = m1*m2;
            cout<<"Result"<<endl;
            cout<<result<<endl;
        }
        else
            throw "wrong dimension error ";
    }
    catch(const char* msg)
    {
        cout<<msg<<endl;
    }
}
void MatrixCalculator::Calc_transpose(Matrix&m1)
{

    cout<<"Result"<<endl;
    Matrix result=m1.transpose();
    cout<<result<<endl;
}

