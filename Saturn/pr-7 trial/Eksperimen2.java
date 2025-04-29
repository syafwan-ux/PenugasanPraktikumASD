class Node {
    int key;
    Node left, right;

    Node(int data) {
        key = data;
        left = right = null;
    }
}

class BST {
    Node root;

    // Recursive insert method
    Node insert(Node root, int key) {
        if (root == null) { // Base case: if tree is empty, create a new node
            return new Node(key);
        }
        if (key < root.key) {
            root.left = insert(root.left, key); // Recur to the left subtree
        } else if (key > root.key) {
            root.right = insert(root.right, key); // Recur to the right subtree
        }
        return root; // Return the unchanged node pointer
    }

    // Recursive search method
    boolean search(Node root, int key) {
        if (root == null) {
            return false; // Base case: tree is empty or key is not found
        }
        if (root.key == key) {
            return true; // Key found
        }
        if (key < root.key) {
            return search(root.left, key); // Recur to the left subtree
        } else {
            return search(root.right, key); // Recur to the right subtree
        }
    }

    // Helper method to insert key starting from the root
    void insert(int key) {
        root = insert(root, key);
    }

    // Helper method to search key starting from the root
    boolean search(int key) {
        return search(root, key);
    }

    public static void main(String[] args) {
        BST tree = new BST();
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);

        System.out.println("Search 30: " + (tree.search(30) ? "Found" : "Not Found"));
        System.out.println("Search 90: " + (tree.search(90) ? "Found" : "Not Found"));
    }
}
