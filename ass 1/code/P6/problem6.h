#ifndef PROBLEM6_H_INCLUDED
#define PROBLEM6_H_INCLUDED

#include <iostream>
#include <string>
#include <vector>

using namespace std;

class StudentName
{

    private:
        string name;
    public:
        int numofspaces = 0;
        StudentName(string nam);


        void print();

        bool Replace(int i, int j);


        bool Replace1(int i,int j)
    {
        vector <string> Names;
        string Name="";
        for(int i=0; i<name.length(); ++i)
        {
            if (Name[i]==' ')
            {
                Names.push_back(Name);
                Name="";
            }
            else if(i==name.length()-1)
            {
                Name+=name[i];
                Names.push_back(Name);
            }
            else
                Name+=name[i];
        }
        if(i > Names.size() || j > Names.size())
        {
            cout << "False" << endl;
            return false;
        }
        else
        {
            string s=Names[j-1];
            Names[j-1]=Names[i-1];
            Names[i-1]=s;
            string f;
            for(int i = 0 ; i < Names.size() ; i++)
            {
                f+=Names[i] + " ";
            }
            name = f;
            cout << name<<endl;
            cout << "True" << endl;
            return true;
        }
    }


};




#endif // PROBLEM6_H_INCLUDED
