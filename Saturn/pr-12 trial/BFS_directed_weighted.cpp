#include <iostream>
#include <array>
#define MAX 5
using namespace std;

int rear = -1;
int front = 0;
int queueCount = 0;

int vertexCount = 0;

struct Vertex {
    char label;
    bool visited;
};

int queue[MAX];
Vertex* lstVertices[MAX];
int adjMatrix[MAX][MAX];

void insertData(int item) {
    queue[++rear] = item;
    queueCount++;
}

int removeData() {
    queueCount--;
    return queue[front++];
}

bool isQueueEmpty() {
    return queueCount == 0;
}

void addVertex(char label) {
    Vertex* vertex = new Vertex;
    vertex->label = label;
    vertex->visited = false;
    lstVertices[vertexCount++] = vertex;
}

// Directed edge with weight
void addEdge(int start, int end, int weight) {
    adjMatrix[start][end] = weight;
}

void displayVertex(int vertexIndex) {
    cout << lstVertices[vertexIndex]->label << " ";
}

int getAdjacencyUnvisited(int vertexIndex) {
    for (int i = 0; i < vertexCount; i++) {
        if (adjMatrix[vertexIndex][i] > 0 && !lstVertices[i]->visited) {
            return i;
        }
    }
    return -1;
}

void breadthFirstSearch() {
    lstVertices[0]->visited = true;
    displayVertex(0);
    insertData(0);
    int unvisitedVertex;
    while (!isQueueEmpty()) {
        int tempVertex = removeData();
        while ((unvisitedVertex = getAdjacencyUnvisited(tempVertex)) != -1) {
            lstVertices[unvisitedVertex]->visited = true;
            displayVertex(unvisitedVertex);
            insertData(unvisitedVertex);
        }
    }
    for (int i = 0; i < vertexCount; i++) {
        lstVertices[i]->visited = false;
    }
}

int main() {
    // Initialize adjacency matrix with 0 (no edge)
    for (int i = 0; i < MAX; i++) {
        for (int j = 0; j < MAX; j++) {
            adjMatrix[i][j] = 0;
        }
    }

    // Add vertices
    addVertex('S');
    addVertex('A');
    addVertex('B');
    addVertex('C');
    addVertex('D');

    // Add directed weighted edges (random weights)
    addEdge(0, 1, 2); // S -> A (weight 2)
    addEdge(0, 2, 4); // S -> B (weight 4)
    addEdge(1, 3, 1); // A -> C (weight 1)
    addEdge(2, 3, 3); // B -> C (weight 3)
    addEdge(3, 4, 5); // C -> D (weight 5)

    cout << "Breadth First Search on Directed Weighted Graph: ";
    breadthFirstSearch();
    return 0;
}
