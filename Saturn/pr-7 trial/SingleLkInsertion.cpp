#include <iostream>
using namespace std;

struct node {
    int data;
    node *next;
};
struct node *head = NULL;
struct node *current = NULL;

void display() {
    struct node *temp = head;
    while (temp != NULL) {
        cout << " " << temp->data;
        temp = temp->next;
    }
}

void insertatbegin(int data) {
    struct node *linked = new struct node;
    linked->data = data;
    linked->next = head;
    head = linked;
};

void insertatend(int data) {
    struct node *linked = new struct node;
    linked->data = data;
    linked->next = NULL;

    if (head == NULL) {
        head = linked;
    } else {
        struct node *temp = head;
        while (temp->next != NULL) {
            temp = temp->next;
        }
        temp->next = linked;
    }
}

void insertMid(struct node *prev_node, int data) {
    if (prev_node == NULL) {
        cout << "Error: Cannot insert after a NULL node in insertMid function.";
        return;
    }
    struct node *linked = new struct node;
    linked->data = data;
    linked->next = prev_node->next;
    prev_node->next = linked;   //mengubah pointer yang semula dari 10 menjadi 50 
                                //yaitu new node yang telah dideklarasikan
}

int main () {
    insertatbegin(10);
    insertatbegin(20);
    insertatend(100);
    insertMid(head->next, 50);

    display();
    return 0;
}