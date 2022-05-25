#include <iostream>
using namespace std;
int binarySearch(int arr[],int size,int item)
{
    int first=0;
    int last=size-1;
    int mid;
    int index=-1;
    while(first<=last)
    {
        mid=(first+last)/2;
        if(mid==0)
        {
            if(item>arr[0]){
                return 1;
                break;
            }
            else{
                return 0;
                break;
            }
        }
        else
        {

             if(item>arr[mid])
                first=mid+1;

            else if(item<arr[mid])
                last=mid-1;
            else
            {
                index=mid;
                 return index;
                break;
            }
        }
    }
}
void insertionSort (int arr[], int size)
{

    for (int i = 1,j; i < size; i++)
    {
        int tmp = arr [i];
        for (j = i; (j > 0 && tmp < arr [j-1]); j--)
        {
            arr [j] = arr [j-1];
        }
        arr [j] = tmp;
    }
    for(int i=0; i<size; i++)
        cout<<arr[i]<<endl;
}
void binaryInsertionSort(int arr[],int size)
{
    int position, selected,j;
    for (int i = 1; i < size; i++)
    {
        j=i-1;
        selected=arr[i];
        position=binarySearch(arr,i,selected);
        while(j>=position)
        {
            arr[j+1] = arr[j];
            j--;
        }
        arr[j+1]=selected;
    }
    for(int i=0; i<size; i++)
        cout<<arr[i]<<endl;
}
int main()
{
    int a[] = {15, 41, 38, 22, 42, 51, 49, 45, 8, 9, 6, 4, 2, 7, 10};
    binaryInsertionSort(a,15);
    cout<<"Insertion Sort"<<endl;
    insertionSort(a,15);
    return 0;
}
