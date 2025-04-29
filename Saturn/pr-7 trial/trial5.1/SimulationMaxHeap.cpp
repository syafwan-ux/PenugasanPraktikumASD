#include <iostream>
#include <bits/stdc++.h>
using namespace std;

// MaxHeap class
// Simulasi antrian pesawat dengan 100 orang penumpang
// menggunakan max heap yaitu priority heap atau nilai prioritas
// variabel berupa nama maskapai, nomor penerbangan, dan kota tujuan
// mau menggunakan array atau linked list?
// decided to use an array
// also dari yang aku pelajari itu kalau menggunakan array
// ganjil itu left child --> karena initially left child itu 2i + 1 1, 3, 5, 7 dst
// genap itu right child --> 2i + 2, 2,4,6,8 dst
// 

int heap[50];
int heapSize = -1;      //because the array is initially empty

int rightChild (int i) {
    return 2*i + 1;
}

int leftChild (int i) {
    return 2*i + 2;
}

int parent (int i) {    //parent yang dimaksud tidak hanya root, tetapi tiap node yang memilikii cabang atau
    return (i-1)/2;     //leftchild dan rightchild
}

void shiftUP (int i){    // int i adalah indeks
    while (i > 0 && heap[parent(i)] < heap[i]) {
        swap(heap[parent(i)], heap[i]);
        i = parent(i);      //
    }
}

void shiftDown (int i) {
    int maxIndex = i;
    int l = leftChild(i);
    if (l <= heapSize && heap[l] > heap[maxIndex]) {
        maxIndex = l;
    }
    int r = rightChild(i);
    if (r <= heapSize && heap[r] > heap[maxIndex]) {
        maxIndex = r;
    }
    if (i != maxIndex) {
        swap(heap[i], heap[maxIndex]);
        shiftDown(maxIndex);
    }
}

int main () {


}