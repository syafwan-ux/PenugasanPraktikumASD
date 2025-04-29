public class Eksperimen1 {
    private int[] tree;
    private int size;

    // Constructor untuk menginisialisasi BST berbasis array
    public Eksperimen1(int capacity) {
        size = capacity;
        tree = new int[capacity];
        for (int i = 0; i < size; i++) {
            tree[i] = -1; // -1 menandakan node kosong
        }
    }

    // Fungsi untuk menyisipkan elemen ke dalam BST berbasis array
    public void insert(int key) {
        int index = 0;
        while (index < size) {
            if (tree[index] == -1) { // Jika posisi kosong, sisipkan elemen
                tree[index] = key;
                return;
            }
            if (key < tree[index]) {
                index = 2 * index + 1; // Pindah ke anak kiri
            } else {
                index = 2 * index + 2; // Pindah ke anak kanan
            }
        }
        System.out.println("Tree is full, cannot insert " + key);
    }

    // Fungsi untuk mencari elemen di dalam BST berbasis array
    public boolean search(int key) {
        int index = 0;
        while (index < size && tree[index] != -1) {
            if (tree[index] == key) { // Jika elemen ditemukan, kembalikan true
                return true;
            }
            if (key < tree[index]) {
                index = 2 * index + 1; // Pindah ke anak kiri
            } else {
                index = 2 * index + 2; // Pindah ke anak kanan
            }
        }
        return false; // Elemen tidak ditemukan
    }

    // Main method untuk menguji BST
    public static void main(String[] args) {
        Eksperimen1 bst = new Eksperimen1(10);
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        
        System.out.println("Search 30: " + (bst.search(30) ? "Found" : "Not Found"));
        System.out.println("Search 90: " + (bst.search(90) ? "Found" : "Not Found"));
    }
}

