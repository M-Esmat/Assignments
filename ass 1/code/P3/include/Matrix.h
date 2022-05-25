#ifndef MATRIX_H
#define MATRIX_H
#include <iostream>
using namespace std;
class Matrix
{
private:
    int** mat;
    int rows;
    int cols;
public:
    Matrix();
    virtual~Matrix();
    Matrix(int x, int y);
    int getrows();
    int getcols();
    friend ostream& operator<<(ostream& output , Matrix& m);
    friend istream& operator>>(istream& input , Matrix& m);
    Matrix operator+(Matrix &m);
    Matrix operator-(Matrix &m);
    Matrix transpose();
    Matrix operator*(Matrix &m);
    friend class MatrixCalculator;
};
#endif // MATRIX_H
