#include "BigDecimalInt.h"
#include <iostream>
#include <vector>
#include <stdlib.h>
#include <sstream>
using namespace std;
BigDecimalInt::BigDecimalInt() {}
BigDecimalInt::BigDecimalInt(string big)
{
    num = big;
    convert();
}
BigDecimalInt::BigDecimalInt(int big)
{
    stringstream s;
    s << big;
    s >> num;
    convert();
}
void BigDecimalInt:: convert()
{
    for(int i=0; i<num.length(); i++)
    {
        if(num[i] == '-')
            negative = true;
        else if(num[i]>47 && num[i]<58)
            value.push_back(num[i] - '0');
        else
        {
            cout << "Wrong input" << endl;
            exit(-1);
        }
    }
}
BigDecimalInt BigDecimalInt:: operator+(BigDecimalInt operand)
{
    int carry=0, sum=0, i=
    value.size()-1, j=operand.value.size()-1;
    BigDecimalInt summation;

    if(negative && operand.negative)
        summation.negative=true;

    while(i>=0||j>=0)
    {
        if(i>=0&&j>=0)
            sum = value[i] + operand.value[j] + carry;
        else if(i>=0)
            sum = value[i] + carry;
        else if(j>=0)
            sum = operand.value[j]+carry;
        carry=0;
        if(sum>9)
        {
            carry = 1;
            sum %= 10;
        }
        summation.value.insert(summation.value.begin(), sum);

        if(((i==j && i==0) || (i==0 && j<0) || (j==0 && i<0)) && carry == 1)
            summation.value.insert(summation.value.begin(), carry);
        i--;
        j--;
    }
    return summation;
}
BigDecimalInt BigDecimalInt:: operator-(BigDecimalInt operand)
{
    int sub=0, i=value.size()-1, j=operand.value.size()-1;
    BigDecimalInt subtraction;

    while(i>=0 && j>=0)
    {
        if((value[0]<operand.value[0] && getSize() == operand.getSize()) || getSize() < operand.getSize())
        {
            sub = operand.value[j--] - value[i--];
            subtraction.negative=true;
        }
        else
            sub = value[i--] - operand.value[j--];
        subtraction.value.insert(subtraction.value.begin(), sub);
    }
    while(i>=0)
        subtraction.value.insert(subtraction.value.begin(), value[i--]);
    while(j>=0)
        subtraction.value.insert(subtraction.value.begin(), operand.value[j--]);
    for(int h=subtraction.value.size()-1; h>0; h--)
    {
        if(subtraction.value[h]<0)
        {
            subtraction.value[h] += 10;
            subtraction.value[h-1] -= 1;
        }
    }
    return subtraction;
}


BigDecimalInt BigDecimalInt:: operator=(BigDecimalInt operand)
{
    BigDecimalInt temp;
    value = temp.value = operand.value;
    negative = temp.negative = operand.negative;
    num = temp.num = operand.num;
    return temp;
}
int BigDecimalInt::getSize()
{
    return value.size();
}

ostream &operator<<(ostream &output, BigDecimalInt &operand)
{
    if(operand.negative)
        output << "-";
    for(int i=0; i<operand.getSize(); i++)
        output << operand.value[i];
    return output;
}
bool BigDecimalInt:: Negative()
{
    return negative;
}
void BigDecimalInt:: setStatus(bool flag)
{
    negative = flag;
}
