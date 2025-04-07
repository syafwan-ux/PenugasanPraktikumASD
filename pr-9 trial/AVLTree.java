//Eksperimen 1

class Node {
    int key, height;
    Node left, right;

    Node(int d) {
        key = d;
        height = 1;
    }
}

public class AVLTree {
    Node root;

    // Function to get height of node
    int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    // Function to get balance factor of node
    int getBalanceFactor(Node node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // Function to print unbalanced nodes (balance factor < -1 or > 1)
    void printUnbalancedNodes() {
        printUnbalancedNodes(root);
    }

    private void printUnbalancedNodes(Node node) {
        if (node != null) {
            int balance = getBalanceFactor(node);
            if (balance < -1 || balance > 1) {
                System.out.println("Unbalanced node: " + node.key + " (Balance factor: " + balance + ")");
            }
            printUnbalancedNodes(node.left);
            printUnbalancedNodes(node.right);
        }
    }

    // Inorder traversal
    void inorder() {
        inorder(root);
    }

    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    // Right rotation
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // Left rotation
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    // Insert node
    Node insert(Node node, int key) {
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        int balance = getBalanceFactor(node);

        // Left Left Case
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Insert method for public use
    void insert(int key) {
        root = insert(root, key);
    }

    // Test the implementation
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Insert test data
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        // Print inorder traversal
        System.out.println("Inorder traversal:");
        tree.inorder();
        System.out.println();

        // Print unbalanced nodes
        System.out.println("\nChecking for unbalanced nodes:");
        tree.printUnbalancedNodes();
    }
}
