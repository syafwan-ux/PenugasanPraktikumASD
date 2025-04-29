import java.util.Random;

class Node {
    int key;
    Node left, right;

    public Node(int key) {
        this.key = key;
        this.left = this.right = null;
    }
}

class BST {
    Node root;

    Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }
        if (key < root.key) {
            root.left = insert(root.left, key);
        } else if (key > root.key) {
            root.right = insert(root.right, key);
        }
        return root;
    }

    boolean search(Node root, int key) {
        if (root == null) {
            return false;
        }
        if (key == root.key) {
            return true;
        }
        return key < root.key ? search(root.left, key) : search(root.right, key);
    }
}

public class Eksperimen3 {
    // Mengukur waktu pencarian
    static long measureSearchTime(BST tree, Node root, int key) {
        long start = System.nanoTime();
        tree.search(root, key);
        return System.nanoTime() - start;
    }

    // Siapkan data random
    static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = rand.nextInt(size);
        return arr;
    }

    // Siapkan data terurut
    static int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = i;
        return arr;
    }

    public static void main(String[] args) {
        // Pilih salah satu opsi variasi jumlah data (N)
        int[] sizes = {100, 200, 400, 600}; // Reduce the size to avoid deep recursion
        int iterations = 3; // Reduce iterations to speed up the program
        Random rand = new Random();

        for (int N : sizes) { // Untuk setiap N
            // Siapkan data
            int[] idealData = generateRandomArray(N);
            int[] sortedData = generateSortedArray(N);

            // Membangun BST Ideal (data acak)
            BST bstIdeal = new BST();
            for (int key : idealData) {
                bstIdeal.root = bstIdeal.insert(bstIdeal.root, key);
            }

            // Membangun BST Worst Case (data terurut)
            BST bstWorst = new BST();
            for (int key : sortedData) {
                bstWorst.root = bstWorst.insert(bstWorst.root, key);
            }

            if (bstIdeal.root == null || bstWorst.root == null) {
                System.err.println("Error: BST root is null. Check data generation.");
                return;
            }

            // Menguji pencarian
            long totalTimeIdeal = 0;
            long totalTimeWorst = 0;

            for (int i = 0; i < iterations; i++) {
                // Siapkan key pencarian 10% data terakhir
                int min = N - (N / 10);
                int max = N - 1; // Ensure max is within bounds
                int searchKey = rand.nextInt(max - min + 1) + min;

                // Hitung waktu BST Ideal
                totalTimeIdeal += measureSearchTime(bstIdeal, bstIdeal.root, searchKey);

                // Hitung waktu BST Worst Case
                totalTimeWorst += measureSearchTime(bstWorst, bstWorst.root, searchKey);
            }

            // Tampilkan perbandingan rata-rata waktu pencarian
            System.out.printf("N = %d, BST Ideal Avg Time = %d ns, BST Worst Avg Time = %d ns%n",
                    N, totalTimeIdeal / iterations, totalTimeWorst / iterations);
        }
    }
}