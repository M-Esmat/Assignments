#include <iostream>
#include <cassert>

using namespace std;

struct Node
{
   int item;
   Node* left;
   Node* right;
};

class BSTree
{
    Node* node;
public:
	BSTree()
	{
        node = NULL;
	}
	bool isEmpty()
	{
	    if (node == NULL)
            return true;
	}

	void insert(int i)
	{
    Node* Cur;
    Node* n = new Node;
    Node* behind;
    assert(n != NULL);
    n->item = i;
    n->left = NULL;
    n->right = NULL;

    if(node == NULL)
       node = n;
    else
    {
       Cur = node;

       while(Cur != NULL)
       {
           behind = Cur;

           if(Cur->item == i)
           {
              return;
           }
           else if(Cur->item > i)
                 Cur = Cur->left;
              else
                 Cur = Cur->right;
       }

       if(behind->item > i)
          behind->left = n;
       else
          behind->right = n;
    }
	}
	void remove(int i)
	{
    Node* Cur;
	Node* behind;

	if(node == NULL)
	{
		cout<<"The tree is empty "<<endl;
		return;
	}
	if(node->item == i)
	{
		deleteFromTree(node);
		return;
	}

	behind = node;

	if(node->item > i)
		Cur = node->left;
	else
		Cur = node->right;

	//search for the item to be deleted.
	while(Cur != NULL)
	{
		if(Cur->item == i)
			break;
		else
		{
			behind = Cur;
			if(Cur->item > i)
				Cur= Cur->left;
			else
				Cur = Cur->right;
		}
	}

	if(Cur == NULL)
		cout<<"Not found in the tree."<<endl;
	else if(behind->item > i)
		deleteFromTree(behind->left);
	else
		deleteFromTree(behind->right);

	}
	void deleteFromTree(Node* &n)
	{
        Node* Cur;
        Node* behind;
        Node* temp;
        if(n->left == NULL && n->right == NULL)
        {
            delete n;
            n = NULL;
        }
        else if(n->left == NULL)
        {
            temp = n;
            n= n->right;
            delete temp;
        }
        else if(n->right == NULL)
        {
            temp = n;
            n = n->left;
            delete temp;
        }
        else
        {
            Cur = n->left;
            behind = NULL;

        while(Cur->right != NULL)
        {
            behind = Cur;
            Cur = Cur->right;
        }

        n->item = Cur->item;

        if(behind==NULL)
           n->left = Cur->left;
        else
           behind->right = Cur->left;

        delete Cur;
     }
	}
	void inorderTraversal()
	{
	    inorder(node);
	}
	void inorder(Node* n)
	{
	    if (n!=NULL)
	    {
	        inorder(n->left);
	        cout<<n->item<<" ";
	        inorder(n->right);
	    }
	}

	void preorderTraversal()
	{
	    preorder(node);
	}
	void preorder(Node* n)
	{
	    if (n!=NULL)
        {
            cout<<n->item<<" ";
            preorder(n->left);
            preorder(n->right);
        }
	}
	void postorderTraversal()
	{
	    postorder(node);
	}
	void postorder(Node* n)
	{
	    if (n!=NULL)
        {
            postorder(n->left);
            postorder(n->right);
            cout<<n->item<<" ";
        }
	}

	~BSTree()
	{
        node=NULL;
	}
	};

int main()
{
    BSTree b;
    b.insert(25);
    b.insert(15);
    b.insert(10);
    b.insert(4);
    b.insert(12);
    b.insert(36);
    b.insert(22);
    b.insert(18);
    b.insert(24);
    b.insert(50);
    b.insert(35);
    b.insert(31);
    b.insert(44);
    b.insert(70);
	b.insert(66);
	b.insert(90);
	b.remove(36);
	cout<<"Inorder: ";
	b.inorderTraversal();
	cout<<endl<<endl;
	cout<<"Postorder: ";
	b.postorderTraversal();
	cout<<endl<<endl;
	cout<<"Preorder: ";
	b.preorderTraversal();
	cout<<endl;
	return 0;
}
