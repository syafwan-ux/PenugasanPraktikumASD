import java.util.*;

class Node {
    int key;
    Node left, right;
    int height;

        Node(int N) {
            key = N;
            left = right = null;
            height = 1;
        }
}

class GFG {

    static int height (Node N) {
        if (N == null) {
            return 0;
        }
        return N.height;
    }

    static Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }
        if (key < node.key) {
            node.left = insert(node.left, key);
        }
        if (key > node.key) {
            node.right = insert(node.right, key);
        }

        int height = Math.max(height(node.left), height(node.right));

        return node;
    }
}

public class AVLTree {
    
}
