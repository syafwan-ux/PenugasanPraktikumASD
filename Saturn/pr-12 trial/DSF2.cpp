#include <iostream>
#include <string>
#include <array>
using namespace std;

int top = -1;
constexpr int MAX = 4;
int vertexCount = 0;

struct Vertex{
    char label;
    bool isVisited;
};

array<int, MAX> stack;
array<Vertex*, MAX> istVertices;            //declaration for each vertex that comes
array<array<int, MAX>, MAX> adjVertices;

void push(int item) {
    stack[++top] = item;
}

int pop() {
    return stack[top--];
}

int peek() {
    return top;
}

bool isStackEmpty() {
    return top == -1;
}

void addVertices (char label) {
    Vertex* vertex = new Vertex;            //do not forget the pointer please
    vertex->label = label;
    vertex->isVisited = false;
    istVertices[vertexCount++] = vertex;
}

void addEdge (int i, int j) {
    adjVertices[i][j] = 1;
    adjVertices[j][i] = 1;
}

void displayVertex(int index) {
    cout << istVertices[index]->label << " ";
}

int getUnvisitedAdjacency(int index) {
    for (int i = 0; i < vertexCount; i++) {
        if (adjVertices[index][i] == 1 && !istVertices[i]->isVisited) {
            return i;
        }
    }
    return -1;
}

void depthfirstsearch() {
    istVertices[0]->isVisited = true;
    displayVertex(0);
    push(0);
    while(!isStackEmpty()) {
        int unvisited = getUnvisitedAdjacency(peek());
        if (unvisited == -1) {
            pop();
        }
        else {
            istVertices[unvisited]->isVisited = true;
            displayVertex(unvisited); 
            push(unvisited);
        }
    }

    for (int i = 0; i < vertexCount; i++) {
        istVertices[i]->isVisited = false;
    }

}

int main() {
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                adjVertices[i][j] = 0;
            }
        }


   addVertices('S');
   addVertices('A');
   //addVertices('B');
   addVertices('C');
   addVertices('D');
   addEdge(0, 1);
   addEdge(0, 2);
   //addEdge(0, 3);
   addEdge(1, 3);
   //addEdge(2, 4);
   addEdge(2, 3);
   cout << "Depth First Search: ";
   depthfirstsearch();
   return 0;
}