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

class StackLinkedList {
private:
    LinkedList list;

public:
    void push(int val) {
        list.insertFront(val);
    }

    void pop() {
        if (list.isEmpty()) {
            std::cout << "Stack is empty!" << std::endl;
            return;
        }
        list.removeFront();
    }

    int top() {
        if (list.isEmpty()) {
            throw std::runtime_error("Stack is empty");
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
    StackLinkedList stack;
    stack.push(10);
    stack.push(20);
    stack.push(30);
    stack.display();
    std::cout << "Top: " << stack.top() << "\n";
    stack.pop();
    stack.display();
    std::cout << "Top: " << stack.top() << "\n";
    stack.pop();
    stack.display();
    std::cout << "Top: " << stack.top() << "\n";
    stack.pop();
    stack.display();
    return 0;
}