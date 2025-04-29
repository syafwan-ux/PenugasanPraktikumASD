#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;

int main () {
    const int SIZE = 8;
    int arr[SIZE];

    srand(time(0));
    for (int i = 0; i < SIZE; i++) {
        arr[i] = rand() % 100 + 1;
    }

    cout << "Random numbers in array: ";
    for (int i = 0; i < SIZE; i++) {
        cout << arr[i] << " ";
    }
    return 0;
}
