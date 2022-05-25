#ifndef MATRIXCALCULATOR_H
#define MATRIXCALCULATOR_H
#include "Matrix.h"
#include <iostream>
using namespace std;
class MatrixCalculator
{
public:
    MatrixCalculator();
    void Menu();
    void addition(Matrix&m1,Matrix&m2);
    void subtraction(Matrix&m1,Matrix&m2);
    void multiplication(Matrix&m1,Matrix&m2);
    void Calc_transpose(Matrix&m1);
};
#endif
