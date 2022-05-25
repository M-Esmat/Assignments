#include <iostream>
using namespace std;
template <class T>
T power_1(T a,int n)
{
    if(a==0)
        return 1;
    if(n==1)
        return a;
    else
        return a*(power_1(a,n-1));
}
template <class T>
T power_2(T a,int n)
{
    if(a==0)
        return 1;
    else if(n==1)
    {
        return a;
    }
    else if(n%2!=0)
    {
        return (power_2(a,(n+1)/2))*(power_2(a,(n-1)/2));
    }
    else
    {
        return(power_2(a,n/2))*(power_2(a,n/2));
    }
}
int main()
{
    cout<<"first function test : 5^9 ="<<endl;
    cout << power_1(5,9) << endl;
    cout<<"second function test: 4^3 ="<<endl;
    cout << power_2(4,3) << endl;
    return 0;
}
