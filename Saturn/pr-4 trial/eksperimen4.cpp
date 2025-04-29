#include <iostream>
using namespace std;
struct Node {
int data;
Node* next;
};
void insertAfter(Node* prev_node, int new_data) {
if (prev_node == nullptr) {
cout << "Node sebelumnya tidak boleh NULL" << endl;
return;
}
Node* new_node = new Node();
new_node->data = new_data;

prev_node->next = new_node;
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
head->next->data = 30;
head->next->next = nullptr;
cout << "Sebelum penyisipan:" << endl;
printList(head);
insertAfter(head, 20);
cout << "Setelah penyisipan:" << endl;
printList(head);
return 0;
}