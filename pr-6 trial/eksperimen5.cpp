#include <iostream>
#include <stack>
#include <queue>
#include <string>
#include <cctype>
#include <sstream>

using namespace std;

// Fungsi untuk menentukan prioritas operator
int getPriority(char op) {
    if (op == '+' || op == '-') return 1;
    if (op == '*' || op == '/') return 2;
    return 0;
}

// Fungsi untuk mengonversi ekspresi infix ke postfix
string infixToPostfix(const string& infix) {
    stack<char> s;
    string postfix;
    for (char ch : infix) {
        if (isdigit(ch)) {
            postfix += ch;
            postfix += ' ';
        } else if (ch == '(') {
            s.push(ch);
        } else if (ch == ')') {
            while (!s.empty() && s.top() != '(') {
                postfix += s.top();
                postfix += ' ';
                s.pop();
            }
            s.pop(); // Pop '('
        } else {
            while (!s.empty() && getPriority(s.top()) >= getPriority(ch)) {
                postfix += s.top();
                postfix += ' ';
                s.pop();
            }
            s.push(ch);
        }
    }
    while (!s.empty()) {
        postfix += s.top();
        postfix += ' ';
        s.pop();
    }
    return postfix;
}

// Fungsi untuk mengevaluasi ekspresi postfix
int evaluatePostfix(const string& postfix) {
    queue<string> q;
    stack<int> s;
    stringstream ss(postfix);
    string token;
    while (ss >> token) {
        q.push(token);
    }
    while (!q.empty()) {
        token = q.front();
        q.pop();
        if (isdigit(token[0])) {
            s.push(stoi(token));
        } else {
            int b = s.top(); s.pop();
            int a = s.top(); s.pop();
            switch (token[0]) {
                case '+': s.push(a + b); break;
                case '-': s.push(a - b); break;
                case '*': s.push(a * b); break;
                case '/': s.push(a / b); break;
            }
        }
    }
    return s.top();
}

int main() {
    string infix = "3+4*(5-2)";
    cout << "--Infix to Postfix--" << endl;
    cout << "Input Infix: " << infix << endl;
    string postfix = infixToPostfix(infix);
    cout << "Postfix: " << postfix << endl;

    cout << "--Eval Postfix--" << endl;
    cout << "Queue: ";
    stringstream ss(postfix);
    string token;
    while (ss >> token) {
        cout << "[" << token << "],";
    }
    cout << endl;

    int result = evaluatePostfix(postfix);
    cout << "Hasil: " << result << endl;

    return 0;
}