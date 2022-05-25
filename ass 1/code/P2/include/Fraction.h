#pragma once
#include <istream>
#include <iostream>
using namespace std;
class Fraction
{
private :
	int numerator, denominator;
public :
	Fraction();
	Fraction(int numerator, int denominator);
	void setNumerator(int numerator);
	void setDenominator(int denominator);
	int getNumerator();
	int getDenominator();
	Fraction  operator + (const Fraction& f);
	Fraction  operator - (const Fraction& f);
	Fraction  operator * (const Fraction& f);
	Fraction  operator / (const Fraction& f);
	bool operator <(const Fraction& f);
	bool operator >(const Fraction& f);
	bool operator ==(const Fraction& f);
	bool operator <=(const Fraction& f);
	bool operator >=(const Fraction& f);
	void reducingFractions(int &n,int &d);
	friend ostream& operator <<(ostream& output, Fraction& f);
	friend istream& operator>>(istream& input, Fraction&);

};

