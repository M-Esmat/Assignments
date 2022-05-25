#include <iostream>
#include <vector>
using namespace std;
vector<string> printed; //to store the varubles that printed
void RecPermute(string soFar, string rest)
{
	int found = 0; //the varuble equle 0 if the word is not printed
	if (rest == "") // No more characters
	{
		for (int i = 0; i < printed.size(); i++)
		{
			if (soFar == printed[i])//check if the word printed
			{
				found = 1;//the varuble equle 1 if the word is  printed
				break;
			}
		}
		if (found == 0) //to check if the word is printed or not
		{
			printed.push_back(soFar);
			cout << soFar << endl;// Print the word
		}
	}
	else // Still more chars
	// For each remaining char
		for (int i = 0; i < rest.length(); i++) {
			string next = soFar + rest[i];// Glue next char
			string remaining = rest.substr(0, i) + rest.substr(i + 1);
			RecPermute(next, remaining);
		}
}
// "wrapper" function
void ListPermutations(string s) {
	RecPermute("", s);
}
int main()
{

	ListPermutations("Makka");//call the function
	return 0;
}

