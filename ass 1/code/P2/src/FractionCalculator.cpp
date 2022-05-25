#include "FractionCalculator.h"
#include "Fraction.h"
FractionCalculator::FractionCalculator()
{
	string userchace1;
	while (true) {
		cout << "1- Perform Fraction Addition" << endl <<
			"2 - Perform Fraction Subtraction" << endl <<
			"3 - Perform Fraction Multiplication" << endl <<
			"4 - Exit" << endl;
		cin >> userchace1;
		Fraction frac1, frac2,frac3;
		if (userchace1 == "1") {
			cin >> frac1 >> frac2;
			frac3 = frac1 + frac2;
			cout << frac3 << endl;
		}
		else if (userchace1 == "2") {
			cin >> frac1 >> frac2;
			frac3 = frac1 - frac2;
			cout << frac3 << endl;
		}
		else if (userchace1 == "3") {
			cin >> frac1 >> frac2;
			frac3 = frac1 * frac2;
			cout << frac3 << endl;
		}
		else if (userchace1 == "4") {
			break;
		}
		else
			cout << "wrong chace!!" << endl;
	}
}
