#include "problem6.h"


StudentName :: StudentName(string nam)
        {
            int pos = 0;
            for (int i = 0; i < nam.size(); i++)
            {
                if (nam[i] == ' ')
                {
                    numofspaces++;
                    pos = i;
                }
            }
            if (numofspaces == 0)
            {
                name = (nam + " " + nam + " " + nam + " ");
            }
            else if (numofspaces == 1 )
            {
                name = nam + nam.substr(pos) + " ";
            }
            else
                name = nam + " ";
        }


void StudentName :: print()
        {
            int num = 1;
            vector<string> vect;
            string n = "";

            for (int i = 0; i < name.size(); i++)
            {
                if ( name[i] != ' ')
                {
                    n += name[i];
                }
                else if (name[i] == ' ' || name[i] == '\0')
                {
                    vect.push_back(n);
                    n = "";
                }}

                for(int i=0; i<vect.size(); i++)
                    {
                        cout << "(" <<  i+1 << ") " << vect[i] << endl;
                    }


            }



bool StudentName :: Replace(int i, int j)
        {


            vector<string> vect;
            string n = "";
            for (int i = 0; i < name.size(); i++)
            {
                if ( name[i] != ' ')
                {
                    n += name[i];
                }
                else if (name[i] == ' ')
                {
                    vect.push_back(n);
                    n = "";
                }
            }

                if (i > vect.size() || j > vect.size() || i == j)
                    return false;
                else
                    {

                        string sp = vect[j - 1];
                        vect[j - 1] = vect [i - 1];
                        vect[i - 1] = sp;
                    }
                    string finall;
                    for (int i = 0; i < vect.size(); i++)
                    {
                        finall += vect[i] + " ";
                    }
                    name = finall;

                    return true;


            }



