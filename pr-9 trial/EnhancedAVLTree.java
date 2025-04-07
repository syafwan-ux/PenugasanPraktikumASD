// Enhanced AVL Tree implementation with all rotation cases
class Node {
    int key, height;
    Node left, right;

    Node(int d) {
        key = d;
        height = 1;
    }
}

public class EnhancedAVLTree {
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

    // Right rotation (LL case)
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // Left rotation (RR case)
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    // Left-Right rotation (LR case)
    Node rotateLeftRight(Node node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    // Right-Left rotation (RL case)
    Node rotateRightLeft(Node node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    // Insert node with rebalancing
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
        if (balance > 1 && key > node.left.key)
            return rotateLeftRight(node);

        // Right Left Case
        if (balance < -1 && key < node.right.key)
            return rotateRightLeft(node);

        return node;
    }

    // Insert method for public use
    void insert(int key) {
        root = insert(root, key);
    }

    // Test all rotation cases
    public static void main(String[] args) {
        EnhancedAVLTree tree = new EnhancedAVLTree();
        
        System.out.println("Testing LL case (30, 20, 10):");
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);
        System.out.print("Inorder traversal: ");
        tree.inorder();
        System.out.println("\nUnbalanced nodes:");
        tree.printUnbalancedNodes();
        
        tree = new EnhancedAVLTree();
        System.out.println("\nTesting RR case (10, 20, 30):");
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        System.out.print("Inorder traversal: ");
        tree.inorder();
        System.out.println("\nUnbalanced nodes:");
        tree.printUnbalancedNodes();
        
        tree = new EnhancedAVLTree();
        System.out.println("\nTesting LR case (30, 10, 20):");
        tree.insert(30);
        tree.insert(10);
        tree.insert(20);
        System.out.print("Inorder traversal: ");
        tree.inorder();
        System.out.println("\nUnbalanced nodes:");
        tree.printUnbalancedNodes();
        
        tree = new EnhancedAVLTree();
        System.out.println("\nTesting RL case (10, 30, 20):");
        tree.insert(10);
        tree.insert(30);
        tree.insert(20);
        System.out.print("Inorder traversal: ");
        tree.inorder();
        System.out.println("\nUnbalanced nodes:");
        tree.printUnbalancedNodes();
    }
}
