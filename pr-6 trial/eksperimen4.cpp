#include <iostream>
#include <ctime>
#include <vector>

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
};

class StackArray {
private:
    int* arr;
    int maxSize;
    int topIndex;

public:
    StackArray(int size) : maxSize(size), topIndex(-1) {
        arr = new int[maxSize];
    }

    ~StackArray() {
        delete[] arr;
    }

    void push(int val) {
        if (topIndex >= maxSize - 1) {
            cout << "Stack overflow!" << endl;
            return;
        }
        arr[++topIndex] = val;
    }

    void pop() {
        if (topIndex < 0) {
            cout << "Stack underflow!" << endl;
            return;
        }
        topIndex--;
    }

    bool isEmpty() {
        return topIndex < 0;
    }
};

class QueueArray {
private:
    int* arr;
    int maxSize;
    int front;
    int back;
    int itemCount;

public:
    QueueArray(int size) : maxSize(size), front(0), back(-1), itemCount(0) {
        arr = new int[maxSize];
    }

    ~QueueArray() {
        delete[] arr;
    }

    void enqueue(int val) {
        if (itemCount >= maxSize) {
            cout << "Queue overflow!" << endl;
            return;
        }
        back = (back + 1) % maxSize;
        arr[back] = val;
        itemCount++;
    }

    void dequeue() {
        if (itemCount <= 0) {
            cout << "Queue underflow!" << endl;
            return;
        }
        front = (front + 1) % maxSize;
        itemCount--;
    }

    bool isEmpty() {
        return itemCount == 0;
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
            cout << "Stack is empty!" << endl;
            return;
        }
        list.removeFront();
    }

    bool isEmpty() {
        return list.isEmpty();
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

    bool isEmpty() {
        return list.isEmpty();
    }
};

void benchmark(int N) {
    const int iterations = 5;
    double stackArrayTime = 0, stackLinkedListTime = 0;
    double queueArrayTime = 0, queueLinkedListTime = 0;

    for (int i = 0; i < iterations; ++i) {
        // Stack Array
        StackArray stackArray(N);
        clock_t start = clock();
        for (int j = 0; j < N; ++j) {
            stackArray.push(j);
        }
        for (int j = 0; j < N; ++j) {
            stackArray.pop();
        }
        stackArrayTime += (double)(clock() - start) / CLOCKS_PER_SEC;

        // Stack Linked List
        StackLinkedList stackLinkedList;
        start = clock();
        for (int j = 0; j < N; ++j) {
            stackLinkedList.push(j);
        }
        for (int j = 0; j < N; ++j) {
            stackLinkedList.pop();
        }
        stackLinkedListTime += (double)(clock() - start) / CLOCKS_PER_SEC;

        // Queue Array
        QueueArray queueArray(N);
        start = clock();
        for (int j = 0; j < N; ++j) {
            queueArray.enqueue(j);
        }
        for (int j = 0; j < N; ++j) {
            queueArray.dequeue();
        }
        queueArrayTime += (double)(clock() - start) / CLOCKS_PER_SEC;

        // Queue Linked List
        QueueLinkedList queueLinkedList;
        start = clock();
        for (int j = 0; j < N; ++j) {
            queueLinkedList.enqueue(j);
        }
        for (int j = 0; j < N; ++j) {
            queueLinkedList.dequeue();
        }
        queueLinkedListTime += (double)(clock() - start) / CLOCKS_PER_SEC;
    }

    cout << "Benchmark for " << N << " elements:" << endl;
    cout << "Stack Array: " << stackArrayTime / iterations << " s" << endl;
    cout << "Stack Linked List: " << stackLinkedListTime / iterations << " s" << endl;
    cout << "Queue Array: " << queueArrayTime / iterations << " s" << endl;
    cout << "Queue Linked List: " << queueLinkedListTime / iterations << " s" << endl;
}

int main() {
    vector<int> testSizes = {50000, 100000, 1000000, 10000000};
    for (int size : testSizes) {
        benchmark(size);
    }
    return 0;
}