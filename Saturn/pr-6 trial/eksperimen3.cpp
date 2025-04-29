#include <iostream>
using namespace std;

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

    int top() {
        if (topIndex < 0) {
            cout << "Stack is empty!" << endl;
            return -1;
        }
        return arr[topIndex];
    }

    bool isEmpty() {
        return topIndex < 0;
    }

    void display() {
        if (topIndex < 0) {
            cout << "Stack is empty!" << endl;
            return;
        }
        for (int i = 0; i <= topIndex; i++) {
            cout << arr[i] << " ";
        }
        cout << endl;
    }
};

int main() {
    StackArray stack(5);
    stack.push(10);
    stack.push(20);
    stack.push(30);
    stack.display();
    cout << "Top: " << stack.top() << endl;
    stack.pop();
    stack.display();
    cout << "Top: " << stack.top() << endl;
    stack.pop();
    stack.display();
    cout << "Top: " << stack.top() << endl;
    stack.pop();
    stack.display();
    return 0;
}