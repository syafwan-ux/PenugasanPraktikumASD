//C++ code for Breadth First Traversal
#include <iostream>
#include <array>
using namespace std;

constexpr int MAX = 5;

struct Vertex {
    char label;
    bool visited;
};

//queue variables
array<int, MAX> queue;
int rear = -1;
int front = 0;
int queueCount = 0;

//graph variables
array<Vertex*, MAX> lstVertices;
array<array<int, MAX>, MAX> adjMatrix;
int vertexCount = 0;

//queue functions
void insert(int data) {
    queue[++rear] = data;
    queueCount++;
}

int removeData() {
    queueCount--;
    return queue[front++];
}

bool isQueueEmpty() {
    return queueCount == 0;
}

//graph functions
void addVertex(char label) {
    Vertex* vertex = new Vertex;
    vertex->label = label;
    vertex->visited = false;
    lstVertices[vertexCount++] = vertex;
}

void addEdge(int start, int end) {
    adjMatrix[start][end] = 1;
    adjMatrix[end][start] = 1;
}

void displayVertex(int vertexIndex) {
    cout << lstVertices[vertexIndex]->label << " ";
}

int getAdjUnvisitedVertex(int vertexIndex) {
    for (int i = 0; i < vertexCount; i++) {
        if (adjMatrix[vertexIndex][i] == 1 && !lstVertices[i]->visited) {
            return i;
        }
    }
    return -1;
}

void breadthFirstSearch() {
    lstVertices[0]->visited = true;
    displayVertex(0);
    insert(0);
    int unvisitedVertex;
    while (!isQueueEmpty()) {
        int tempVertex = removeData();
        while ((unvisitedVertex = getAdjUnvisitedVertex(tempVertex)) != -1) {
            lstVertices[unvisitedVertex]->visited = true;
            displayVertex(unvisitedVertex);
            insert(unvisitedVertex);
        }
    }
    for (int i = 0; i < vertexCount; i++) {
        lstVertices[i]->visited = false;
    }
}

int main() {
    for (int i = 0; i < MAX; i++) {
        for (int j = 0; j < MAX; j++) {
            adjMatrix[i][j] = 0;
        }
    }
    addVertex('S');
    addVertex('A');
    addVertex('B');
    addVertex('C');
    addVertex('D');
    addEdge(0, 1);
    addEdge(0, 2);
    addEdge(0, 3);
    addEdge(1, 4);
    addEdge(2, 4);
    addEdge(3, 4);
    cout << "Breadth First Search: ";
    breadthFirstSearch();
    return 0;
}
