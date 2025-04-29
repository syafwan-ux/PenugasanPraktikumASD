#include <iostream>
using namespace std;

struct node {
    int data;
    struct node *leftchild;
    struct node *rightchild;
};
struct node *root = NULL;

void insert(int data) {
    struct node *linked = new struct node;
    linked->data = data;
    linked->leftchild = NULL;
    linked->rightchild = NULL;

    if (root == NULL) {     //tree
        root = linked;
    } else {
        struct node *current = root;
        struct node *parent = NULL;
        while (true) {
            parent = current;
            if (data < current->data) {
                current = current->leftchild;
                if (current == NULL) {
                    parent->leftchild = linked;
                    return;
                }
            } else {
                current = current->rightchild;
                if (current == NULL) {
                    parent->rightchild = linked;
                    return;
                }
            }
        }
    }
}

//okay there will be traversal with three different types
//which is inorder, preorder, and postorder
void inorder_traversal(struct node *root) { //print inorder traversal
    if (root != NULL) {
        inorder_traversal(root->leftchild);
        cout << " " << root->data;
        inorder_traversal(root->rightchild);
    }
}
void preorder_traversal(struct node *root) { //print preorder traversal
    if (root != NULL) {
        cout << " " << root->data;
        preorder_traversal(root->leftchild);
        preorder_traversal(root->rightchild);
    }
}
void postorder_traversal(struct node *root) { //print postorder traversal
    if (root != NULL) {
        postorder_traversal(root->leftchild);
        postorder_traversal(root->rightchild);
        cout << " " << root->data;
    }
}

int main() {
    int i;
    int arr[] = {30, 50, 70, 20, 40, 60, 80};
    for (int i = 0; i < 7; i++) {
        insert(arr[i]);
    }
    inorder_traversal(root);
}