#include <iostream>
using namespace std;
int main() {
int* p = new int; // Alokasi memori di heap
*p = 50;
cout << "Nilai p: " << *p << endl;
cout << "Alamat p: " << p << endl;
delete p; // Menghapus memori
p = nullptr; // Menghindari dangling pointer

cout << "nilai p adalah " << *p << endl;
return 0;
}