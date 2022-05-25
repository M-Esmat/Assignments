#include "Fraction.h"

Fraction::Fraction()
{
	numerator = 1;
	denominator = 1;
}

Fraction::Fraction(int numerator, int denominator)
{
	this->denominator = denominator;
	this->numerator = numerator;
}

void Fraction::setNumerator(int numerator)
{
	this->numerator = numerator;
}

void Fraction::setDenominator(int denominator)
{
	this->denominator = denominator;
}

int Fraction::getNumerator()
{
	return numerator;
}

int Fraction::getDenominator()
{
	return denominator;
}

Fraction Fraction::operator+(const Fraction& f)
{
	Fraction f2;
	f2.numerator = this->numerator * f.denominator
		+ f.numerator * this->denominator;
	f2.denominator = this->denominator * f.denominator;
	reducingFractions(f2.numerator, f2.denominator);
	return f2;
}

Fraction Fraction::operator-(const Fraction& f)
{
	Fraction f2;
	f2.numerator = this->numerator * f.denominator
		- f.numerator * this->denominator;
	f2.denominator = this->denominator * f.denominator;
	reducingFractions(f2.numerator, f2.denominator);
	return f2;
}

Fraction Fraction::operator*(const Fraction& f)
{
	Fraction f2;
	f2.numerator = this->numerator * f.numerator;
	f2.denominator = this->denominator * f.denominator;
	reducingFractions(f2.numerator, f2.denominator);
	return f2;
}

Fraction Fraction::operator/(const Fraction& f)
{
	Fraction f2;
	f2.numerator = this->numerator * f.denominator;
	f2.denominator = this->denominator * f.numerator;
	reducingFractions(f2.numerator, f2.denominator);
	return f2;
}

bool Fraction::operator<(const Fraction& f)
{
	if (((double)this->numerator / (double)this->denominator) < ((double)f.numerator / (double)f.denominator))
		return true;
	else
		return false;
}

bool Fraction::operator>(const Fraction& f)
{
	if (((double)this->numerator / (double)this->denominator) > ((double)f.numerator / (double)f.denominator))
		return true;
	else
		return false;
}

bool Fraction::operator == (const Fraction& f)
{
	if (((double)this->numerator / (double)this->denominator) == ((double)f.numerator / (double)f.denominator))
		return true;
	else
		return false;
}

bool Fraction::operator<=(const Fraction& f)
{
	if (((double)this->numerator / (double)this->denominator) < ((double)f.numerator / (double)f.denominator) || ((double)this->numerator / (double)this->denominator) == ((double)f.numerator / (double)f.denominator))
		return true;
	else
		return false;
}

bool Fraction::operator>=(const Fraction& f)
{
	if (((double)this->numerator / (double)this->denominator) > ((double)f.numerator / (double)f.denominator) || ((double)this->numerator / (double)this->denominator) == ((double)f.numerator / (double)f.denominator))
		return true;
	else
		return false;
}


void Fraction::reducingFractions(int & n,int & d)
{
	for (int i = 2; i <= (n * d); i++)
	{
		if ((n % i == 0) && (d % i == 0)) {
			d /= i;
			n /= i;
		}
	}
}



ostream& operator<<(ostream& output, Fraction& f)
{
	output << f.numerator << "/" << f.denominator;
	return output;
	// TODO: insert return statement here
}

istream& operator>>(istream& input, Fraction& f)
{
	string n;
	input >> f.numerator>> n>> f.denominator  ;
	return input;
	// TODO: insert return statement here
}

