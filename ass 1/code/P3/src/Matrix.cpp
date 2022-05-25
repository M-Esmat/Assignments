#include "Matrix.h"
#include <iostream>
using namespace std;
Matrix::Matrix()
{}
Matrix::~Matrix()
{
    for(int i=0 ; i<rows; i++)
    {
        delete [] mat[i];
    }
    delete [] mat;
}
Matrix::Matrix(int x,int y)
{
    rows = x;
    cols = y;
    mat = new int*[rows];
    for(int i=0; i<rows; i++)
        mat[i] = new int[cols];
}
int Matrix::getrows()
{
    return rows;
}
int Matrix::getcols()
{
    return cols;
}
ostream& operator<<(ostream&output , Matrix& m)
{
    for(int i=0; i<m.getrows(); i++)
    {
        for(int g=0; g<m.getcols(); g++)
            output << m.mat[i][g] << " ";
        output << "\n";
    }
    output<<"-----------------------"<<"\n";
    return output;
}
istream& operator>>(istream& input , Matrix& m)
{
    for(int i=0; i<m.getrows(); i++)
    {
        for(int g=0; g<m.getcols(); g++)
            input>> m.mat[i][g];
    }
    return input;
}
Matrix Matrix:: operator+(Matrix &m)
{
    Matrix result(rows,cols);
    for(int i=0;i<rows;i++)
    {
        for(int g=0;g<cols;g++)
                result.mat[i][g]=mat[i][g]+m.mat[i][g];
    }
    return result;
}

Matrix Matrix:: operator- (Matrix&m)
{
    Matrix result(rows,cols);
    for(int i=0;i<rows;i++)
    {
        for(int g=0;g<cols;g++)
            result.mat[i][g]=mat[i][g]-m.mat[i][g];
    }
    return result;
}
Matrix Matrix::transpose()
{
    int temp;
    Matrix result(cols,rows);
    for(int i=0;i<rows;i++)
    {
        for(int g=0;g<cols;g++)
        {
            temp=mat[i][g];
            result.mat[g][i]=temp;
        }
    }
    return result;
}
Matrix Matrix::operator*(Matrix&m)
{
    Matrix result(rows,m.cols);
    for(int i=0;i<result.rows;i++)
    {
        for(int g=0;g<result.cols;g++){
            result.mat[i][g]=0;
            for(int k=0;k<cols;k++)
                result.mat[i][g]+=mat[i][k]*m.mat[k][g];
        }
    }
    return result;
}
