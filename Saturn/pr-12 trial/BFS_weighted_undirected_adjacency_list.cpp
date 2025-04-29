#include <iostream>
#include <array>
#define MAX 5
using namespace std;

struct Vertex {
    char label;
};

int vertexCount = 0;
Vertex* lstVertices[MAX];
int adjMatrix[MAX][MAX];

void addVertex(char label) {
    Vertex* vertex = new Vertex;
    vertex->label = label;
    lstVertices[vertexCount++] = vertex;
}

void addEdge(int start, int end, int weight) {
    adjMatrix[start][end] = weight;
    adjMatrix[end][start] = weight;
}

void printAdjacencyList() {
    cout << "Adjacency List of the Weighted Undirected Graph:" << endl;
    for (int i = 0; i < vertexCount; i++) {
        cout << lstVertices[i]->label << " -> ";
        bool first = true;
        for (int j = 0; j < vertexCount; j++) {
            if (adjMatrix[i][j] > 0) {
                if (!first) cout << ", ";
                cout << "(" << lstVertices[j]->label << ", " << adjMatrix[i][j] << ")";
                first = false;
            }
        }
        cout << endl;
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

    // Add undirected weighted edges (same as BFS_weighted_undirected.cpp)
    addEdge(0, 1, 2); // S - A (weight 2)
    addEdge(0, 2, 4); // S - B (weight 4)
    addEdge(1, 3, 1); // A - C (weight 1)
    addEdge(2, 3, 3); // B - C (weight 3)
    addEdge(3, 4, 5); // C - D (weight 5)

    printAdjacencyList();

    return 0;
}
