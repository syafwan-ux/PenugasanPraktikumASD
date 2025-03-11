#include <iostream>
using namespace std;

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

    int getFront() {
        if (!head) throw runtime_error("List is empty");
        return head->data;
    }

    void removeFront() {
        if (!head) return;
        Node* temp = head;
        head = head->next;
        delete temp;
        if (!head) tail = nullptr;
    }

    bool isEmpty() {
        return head == nullptr;
    }

    void display() {
        Node* temp = head;
        while (temp) {
            cout << temp->data << " -> ";
            temp = temp->next;
        }
        cout << "NULL" << endl;
    }
};

class QueueLinkedList {
private:
    LinkedList list;

public:
    void enqueue(int val) {
        list.insertBack(val);
    }

    void dequeue() {
        if (list.isEmpty()) {
            cout << "Queue is empty!" << endl;
            return;
        }
        list.removeFront();
    }

    int front() {
        if (list.isEmpty()) {
            cout << "Queue is empty!" << endl;
            return -1;
        }
        return list.getFront();
    }

    bool isEmpty() {
        return list.isEmpty();
    }

    void display() {
        list.display();
    }
};

int main() {
    QueueLinkedList queue;
    queue.enqueue(10);
    queue.enqueue(20);
    queue.enqueue(30);
    queue.display();
    cout << "Front: " << queue.front() << "\n";
    queue.dequeue();
    queue.display();
    cout << "Front: " << queue.front() << "\n";
    queue.dequeue();
    queue.display();
    cout << "Front: " << queue.front() << "\n";
    queue.dequeue();
    queue.display();
    return 0;
}