#include <iostream>
class Node {
    public:
    int data;
    Node* next;
    Node(int val) : data(val), next(nullptr) {}
    };
    class LinkedList {
    private:
    Node* head;
    Node* tail;
public:
LinkedList() : head(nullptr), tail(nullptr) {}
~LinkedList() {
while (head) {
removeFront();
}
}
void insertFront(int val) {
Node* newNode = new Node(val);
if (!head) {
head = tail = newNode;
} else {
newNode->next = head;
head = newNode;
}
}
void insertBack(int val) {
Node* newNode = new Node(val);
if (!head) {
head = tail = newNode;
} else {
tail->next = newNode;
tail = newNode;
}
}
void insertMiddle(int val, int position) {
if (position <= 0 || !head) {
insertFront(val);
return;
}
Node* newNode = new Node(val);
Node* temp = head;
for (int i = 1; temp->next && i < position; ++i) {
temp = temp->next;
}
newNode->next = temp->next;
temp->next = newNode;
if (newNode->next == nullptr) {
tail = newNode;
}
}
int getFront() {
if (!head) throw std::runtime_error("List is empty");
return head->data;
}
int getBack() {
if (!tail) throw std::runtime_error("List is empty");
return tail->data;
}
void removeFront() {
if (!head) return;
Node* temp = head;
head = head->next;
delete temp;
if (!head) tail = nullptr;
}
void removeBack() {
if (!head) return;
if (head == tail) {
delete head;
head = tail = nullptr;
return;
}
Node* temp = head;
while (temp->next != tail) {
temp = temp->next;
}
delete tail;
tail = temp;
tail->next = nullptr;
}
bool isEmpty() {
return head == nullptr;
}
void display() {
    Node* temp = head;
    while (temp) {
    std::cout << temp->data << " -> ";
    temp = temp->next;
    }
    std::cout << "NULL" << std::endl;
    }
    };
    int main() {
    LinkedList list;
    list.insertFront(10);
    list.insertBack(20);
    list.insertBack(30);
    list.insertMiddle(15, 1);
    list.display();
    std::cout << "Front: " << list.getFront() << "\n";
    std::cout << "Back: " << list.getBack() << "\n";
    list.removeFront();
    list.display();
    list.removeBack();
    list.display();
    return 0;
    }