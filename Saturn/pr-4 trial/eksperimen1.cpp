#include <iostream>
using namespace std;
int main() {
int x = 10;
int* p = &x;
int* q = p; // Menyimpan alamat yang sama di variabel lain
cout << "Nilai x: " << x << endl;
cout << "Alamat x: " << &x << endl;
cout << "Pointer p: " << p << " (menunjuk ke x)" << endl;
cout << "Pointer q: " << q << " (menunjuk ke alamat sama)" << endl;
*p = 20; // Mengubah nilai melalui pointer

cout << "Nilai x setelah diubah lewat pointer: " << x << endl;
return 0;
}
