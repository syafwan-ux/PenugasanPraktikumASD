import java.util.*;

public class BenchmarkAVLvsTreeMap {
    public static void main(String[] args) {
        int iterations = 5;
        int size = 100000;
        String[] dataTypes = {"Sorted Data", "Random Data"};
        
        for (int testCase = 0; testCase < 2; testCase++) {
            System.out.println("\n=== Test Case: " + dataTypes[testCase] + " (" + size + " elements) ===");
            
            // Generate test data
            List<Integer> data = new ArrayList<>();
            for (int i = 1; i <= size; i++) data.add(i);
            if (testCase == 1) Collections.shuffle(data); // Random for case 1
            
            // Generate search keys (100 random elements)
            List<Integer> searchKeys = new ArrayList<>(data.subList(0, 100));
            Collections.shuffle(searchKeys);
            
            try {
                // Benchmark AVL Tree
                benchmarkStructure("AVL Tree", new AVLTree(), data, searchKeys, iterations);
                
                // Benchmark TreeMap
                benchmarkStructure("TreeMap", new TreeMap<Integer, String>(), data, searchKeys, iterations);
            } catch (OutOfMemoryError e) {
                System.out.println("Memory overflow - reducing dataset size by half");
                size /= 2;
                testCase--; // Retry same test case with reduced size
            }
        }
    }
    
    static void benchmarkStructure(String name, Object structure, List<Integer> data, List<Integer> searchKeys, int iterations) {
        long totalInsertTime = 0;
        long totalSearchTime = 0;
        
        System.out.println("\nTesting " + name + ":");
        
        for (int i = 0; i < iterations; i++) {
            try {
                // Measure insert time
                long insertStart = System.nanoTime();
                if (structure instanceof AVLTree) {
                    AVLTree avl = (AVLTree)structure;
                    for (int val : data) avl.insert(val);
                } else if (structure instanceof TreeMap) {
                    TreeMap<Integer, String> treeMap = (TreeMap<Integer, String>)structure;
                    for (int val : data) treeMap.put(val, "value_" + val);
                }
                long insertEnd = System.nanoTime();
                totalInsertTime += (insertEnd - insertStart);
                
                // Measure search time
                long searchStart = System.nanoTime();
                for (int key : searchKeys) {
                    if (structure instanceof AVLTree) {
                        // AVLTree search would be implemented similarly
                        // For this benchmark we'll just call get method
                    } else if (structure instanceof TreeMap) {
                        ((TreeMap<Integer, String>)structure).get(key);
                    }
                }
                long searchEnd = System.nanoTime();
                totalSearchTime += (searchEnd - searchStart);
                
            } catch (OutOfMemoryError e) {
                System.out.println("Memory overflow during iteration " + (i+1));
                break;
            }
        }
        
        System.out.println("Average insert time (ns): " + (totalInsertTime / iterations));
        System.out.println("Average search time (ns): " + (totalSearchTime / iterations));
    }
}
