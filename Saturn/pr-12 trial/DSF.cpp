//C++ code for Depth First Traversal
#include <iostream>
#include <array>
#include <vector>
using namespace std;

constexpr int MAX = 5;                    //jumlah vertex
struct Vertex {                           //inisialisasi vertex apakah sudah dikunjungi atau belum
   char label;
   bool visited;
};

//stack variables
array<int, MAX> stack;                    //stack-array dengan ukuran MAX, yaitu 5
int top = -1;                             //top dari suatu data stack

//graph variables
//array of vertices 
array<Vertex*, MAX> lstVertices;          //stack array IstVertices, menyimpan pointer dari struct vertex
                                          //dengan jumlah MAX

//adjacency matrix
array<array<int, MAX>, MAX> adjMatrix;    //Matriks dua dimensi(?)

//vertex count                         
int vertexCount = 0;                      //menyimpan berapa vertex yang ada

//stack functions
void push(int item) {                     //function, dengan parameter item
   stack[++top] = item;                   //item termasuk label(?) dan disimpan ke stack 
}
                                         //item bukan pointer(?)
int pop() {
   return stack[top--];
}

int peek() {
   return stack[top];
}

bool isStackEmpty() {
   return top == -1;
}

//graph functions
//add vertex to the vertex list
void addVertex(char label) {
   Vertex* vertex = new Vertex;
   vertex->label = label;
   vertex->visited = false;
   lstVertices[vertexCount++] = vertex;         //untuk menambah atau mendeklarasi vertex 
}

//add edge to edge array   
void addEdge(int start, int end) {        //mengatur edge untuk tiap vertex   
   adjMatrix[start][end] = 1;             //menggunakan tabel 2X2
   adjMatrix[end][start] = 1;
}

//display the vertex
void displayVertex(int vertexIndex) {     //menampakkan hasil dari DFS
   cout << lstVertices[vertexIndex]->label << " ";    //mengambil 
}

//get the adjacent unvisited vertex
int getAdjUnvisitedVertex(int vertexIndex) {          //terbatas untuk current vertex yang sedang dilalui
   for (int i = 0; i < vertexCount; i++) {
      if (adjMatrix[vertexIndex][i] == 1 && !lstVertices[i]->visited) {
         return i;
      }
   }
   return -1;
}

//mark first node as visited
void depthFirstSearch() {
   lstVertices[0]->visited = true;
   //display the vertex
   displayVertex(0);
   //push vertex index in stack
   push(0);
   while (!isStackEmpty()) {
       //get the unvisited vertex of vertex which is at top of the stack
      int unvisitedVertex = getAdjUnvisitedVertex(peek());
      //no adjacent vertex found
      if (unvisitedVertex == -1) {
         pop();
      } else {
         lstVertices[unvisitedVertex]->visited = true;
         displayVertex(unvisitedVertex);
         push(unvisitedVertex);
      }
   }
   //stack is empty, search is complete, reset the visited flag
   for (int i = 0; i < vertexCount; i++) {
      lstVertices[i]->visited = false;
   }
}
int main() {
   for (int i = 0; i < MAX; i++) {   //set adjacency
      for (int j = 0; j < MAX; j++) {    // matrix to 0
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
   cout << "Depth First Search: ";
   depthFirstSearch();
   return 0;
}
