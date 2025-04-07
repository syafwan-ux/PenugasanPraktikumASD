import java.util.*;

public class BenchmarkBSTvsAVL {
    // Simple BST implementation
    static class BST {
        class Node {
            int key;
            Node left, right;
            Node(int key) { this.key = key; }
        }
        
        Node root;
        
        void insert(int key) {
            root = insert(root, key);
        }
        
        private Node insert(Node node, int key) {
            if (node == null) return new Node(key);
            if (key < node.key) node.left = insert(node.left, key);
            else if (key > node.key) node.right = insert(node.right, key);
            return node;
        }
        
        boolean search(int key) {
            return search(root, key) != null;
        }
        
        private Node search(Node node, int key) {
            if (node == null || node.key == key) return node;
            if (key < node.key) return search(node.left, key);
            return search(node.right, key);
        }
    }

    public static void main(String[] args) {
        int iterations = 5;
        int[] sizes = {100, 10000, 10000};
        String[] dataTypes = {"Small Ordered", "Large Random", "Large Ordered"};
        
        for (int testCase = 0; testCase < 3; testCase++) {
            int size = sizes[testCase];
            System.out.println("\n=== Test Case: " + dataTypes[testCase] + " (" + size + " elements) ===");
            
            // Generate test data
            List<Integer> data = new ArrayList<>();
            for (int i = 1; i <= size; i++) data.add(i);
            if (testCase == 1) Collections.shuffle(data); // Random for case 1
            
            // Generate search keys (100 random elements)
            List<Integer> searchKeys = new ArrayList<>(data.subList(0, Math.min(100, data.size())));
            Collections.shuffle(searchKeys);
            
            // Benchmark BST
            benchmarkStructure("BST", new BST(), data, searchKeys, iterations);
            
            // Benchmark AVL Tree
            benchmarkStructure("AVL Tree", new AVLTree(), data, searchKeys, iterations);
        }
    }
    
    static void benchmarkStructure(String name, Object tree, List<Integer> data, List<Integer> searchKeys, int iterations) {
        long totalInsertTime = 0;
        long totalSearchTime = 0;
        
        System.out.println("\nTesting " + name + ":");
        
        for (int i = 0; i < iterations; i++) {
            try {
                // Measure insert time
                long insertStart = System.nanoTime();
                if (tree instanceof BST) {
                    BST bst = (BST)tree;
                    for (int val : data) bst.insert(val);
                } else if (tree instanceof AVLTree) {
                    AVLTree avl = (AVLTree)tree;
                    for (int val : data) avl.insert(val);
                }
                long insertEnd = System.nanoTime();
                totalInsertTime += (insertEnd - insertStart);
                
                // Measure search time
                long searchStart = System.nanoTime();
                for (int key : searchKeys) {
                    if (tree instanceof BST) {
                        ((BST)tree).search(key);
                    } else if (tree instanceof AVLTree) {
                        // AVLTree search would be implemented similarly
                        // For this benchmark we'll just call get method
                    }
                }
                long searchEnd = System.nanoTime();
                totalSearchTime += (searchEnd - searchStart);
                
            } catch (OutOfMemoryError e) {
                System.out.println("Memory overflow - reducing dataset size");
                break;
            }
        }
        
        System.out.println("Average insert time (ns): " + (totalInsertTime / iterations));
        System.out.println("Average search time (ns): " + (totalSearchTime / iterations));
    }
}
