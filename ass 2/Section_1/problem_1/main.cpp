#include<iostream>
using namespace std;
void merge(int arr[], int left, int middle, int right)
{
	int k;
	int i=0,j=0;
	int size1 = middle - left + 1;
	int size2 = right - middle;
	int* L=new int[size1];
    int* R=new int[size2];

	for (; i < size1; i++)
		L[i] = arr[left + i];
	for (; j < size2; j++)
		R[j] = arr[middle + 1 + j];


	i = 0;
	j = 0;
	k = left;
	while (i < size1 && j < size2)
	{
		if (L[i] <= R[j])
		{
			arr[k] = L[i];
			i++;
		}
		else
		{
			arr[k] = R[j];
			j++;
		}
		k++;
	}

	while (i < size1)
	{
		arr[k] = L[i];
		i++;
		k++;
	}

	while (j < size2)
	{
		arr[k] = R[j];
		j++;
		k++;
	}
}


void mergeSort(int arr[], int left, int right)
{
	if (left < right)
	{
		int middle = left + (right - left) / 2;

		mergeSort(arr, left, middle);
		mergeSort(arr, middle + 1, right);

		merge(arr, left, middle, right);
	}
}

void printMergeOrQuickSort(int a[], int n)
{
	for (int i = 0; i < n; i++)
	{
		cout <<a[i]<< " ";
	}
	cout << endl;

}
int partition1(int a[], int x, int y)
{
	int p1 = a[x];
	int i = x;
	int j = y;
	while (i < j)
	{
		do
		{
			i++;
		} while (a[i] <= p1);
		do
		{
			j--;
		} while (a[j] > p1);

		if (i < j)
			swap(a[i], a[j]);
	}
	swap(a[x], a[j]);
	return j;
}

void quickSort1(int a[], int x, int y)
{

	if (x < y) {
		int piv = partition1(a, x, y);
		quickSort1(a, x, piv);
		quickSort1(a, piv + 1, y);
	}

}



int partition2(int a[], int start, int last) {
	int i = start;
	int j = last;
	int pivv = i;
	while (true)
	{
		while (a[pivv] <= a[j] && pivv != j)
		{
			j--;
		}
		if (pivv == j)
			break;
		else if (a[pivv] > a[j])
		{
			swap(a[j], a[pivv]);
			pivv = j;
		}

		while (a[pivv] >= a[i] && pivv != i)
		{
			i++;
		}
		if (pivv == i)
			break;
		else if (a[pivv] < a[i])
		{
			swap(a[i], a[pivv]);
			pivv = i;
		}
	}
	return pivv;
}


void quickSort2(int a[], int x, int y)
{

	if (x < y) {
		int piv = partition2(a, x, y);
		quickSort2(a, x, piv - 1);
		quickSort2(a, piv + 1, y);
	}

}



int main()
{
    //Merge sort
    cout<<"Merge Sort \n";
    int n1;
    cout<<"Enter array size \n";
    cin>>n1;
    int m_arr[n1];
    cout<<"Enter array elements\n";
    for (int i=0;i<n1;i++)
        cin>>m_arr[i];
    mergeSort(m_arr, 0, n1-1);
    printMergeOrQuickSort(m_arr,n1);
    cout<<endl<<endl;

    //Quick sort
    cout<<"Quick Sort \n";
    int n2;
    cout<<"Enter array size \n";
    cin>>n2;
    int q_arr[n2];
    cout<<"Enter array elements\n";
    for (int i=0;i<n2;i++)
        cin>>q_arr[i];

	quickSort2(q_arr, 0, n2 - 1);
	printMergeOrQuickSort(q_arr,n2);

	return 0;

	}
