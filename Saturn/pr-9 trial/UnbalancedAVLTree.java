// AVL Tree implementation that shows unbalanced nodes before rotations
class Node {
    int key, height;
    Node left, right;

    Node(int d) {
        key = d;
        height = 1;
    }
}

public class UnbalancedAVLTree {
    Node root;

    int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    int getBalanceFactor(Node node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

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

    // Insert without balancing
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
        return node;
    }

    void insert(int key) {
        root = insert(root, key);
    }

    public static void main(String[] args) {
        UnbalancedAVLTree tree = new UnbalancedAVLTree();
        
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
