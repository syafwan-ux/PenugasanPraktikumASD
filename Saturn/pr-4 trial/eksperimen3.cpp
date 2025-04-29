#include <iostream>
using namespace std;
struct Node {
int data;
Node* next;
};
void printList(Node* head) {
Node* temp = head;
while (temp != nullptr) {
cout << temp->data << " -> ";
temp = temp->next;
}
cout << "NULL" << endl;
}
int main() {
// Membuat node pertama
Node* head = new Node();
head->data = 10;
head->next->next = nullptr;
// Menambahkan node kedua
Node* second = new Node();
second->data = 20;
second->next = nullptr;
head->next = second;
// Menampilkan list
printList(head);
return 0;
}