#include <iostream>
using namespace std;

struct Node {
int data;
Node* next;
};

void deleteNode(Node*& head, int key) {
Node* temp = head;
Node* prev = nullptr;

if (temp != nullptr && temp->data == key) {
head = temp->next;
delete temp;
return;
}

while (temp != nullptr && temp->data != key) {
prev = temp;
temp = temp->next;
}

if (temp == nullptr) return;

prev->next = temp->next;
delete temp;
}

void printList(Node* head) {
Node* temp = head;
while (temp != nullptr) {
cout << temp->data << " -> ";
temp = temp->next;
}
cout << "NULL" << endl;
}

int main() {
Node* head = new Node();
head->data = 10;
head->next = new Node();
head->next->data = 20;
head->next->next = new Node();
head->next->next->data = 30;
head->next->next->next = nullptr;

cout << "Sebelum dihapus:" << endl;
printList(head);
deleteNode(head, 20);

cout << "Setelah dihapus:" << endl;
printList(head);

return 0;
}