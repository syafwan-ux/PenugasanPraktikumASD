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

void addEdge(int start, int end) {                  //untuk undirected graph
    adjMatrix[start][end] = 1;                      //apabila directed? mungkin cuman satu 
    adjMatrix[end][start] = 1;                      //kalau weighted gimana?
}

void displayVertex(int vertexIndex) {
    cout << lstVertices[vertexIndex]->label << " ";
}

int getAdjacencyUnvisited(int vertexIndex) {
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
