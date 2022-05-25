#include <iostream>
#include <vector>
#include <stdlib.h>
using namespace std;
class BigDecimalInt{
private:
    vector<int> value;
    string num;
    bool negative=false;
public:
    BigDecimalInt();
    BigDecimalInt(string big);
    BigDecimalInt(int big);
    void convert();
    BigDecimalInt operator+(BigDecimalInt operand);
    BigDecimalInt operator-(BigDecimalInt operand);
    BigDecimalInt operator=(BigDecimalInt operand);
    int getSize();
    friend ostream &operator<<(ostream &output, BigDecimalInt &operand);
    bool Negative();
    void setStatus(bool flag);
};
