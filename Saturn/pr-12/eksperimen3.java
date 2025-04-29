import java.util.PriorityQueue;
import java.util.Random;

public class eksperimen3 {

    public static void main(String[] args) {
        int smallSize = 100;
        int largeSize = 100000;

        int[] smallRandom = generateRandomArray(smallSize);
        int[] largeRandom = generateRandomArray(largeSize);
        int[] sortedData = generateSortedArray(largeSize);

        System.out.println("=== Small Random Data (100 elements) ===");
        runExperiment(smallRandom);

        System.out.println("\n=== Large Random Data (100,000 elements) ===");
        runExperiment(largeRandom);

        System.out.println("\n=== Sorted Data (1 to 100,000) ===");
        runExperiment(sortedData);
    }

    private static void runExperiment(int[] data) {
        // Test HeapTree
        long start, end;

        // Insert
        HeapTree heapTree = new HeapTree(data.length, true);
        start = System.nanoTime();
        for (int val : data) {
            heapTree.insert(val);
        }
        end = System.nanoTime();
        System.out.println("HeapTree insert time: " + (end - start) + " ns");

        // Extract
        start = System.nanoTime();
        for (int i = 0; i < data.length; i++) {
            heapTree.extractRoot();
        }
        end = System.nanoTime();
        System.out.println("HeapTree extract time: " + (end - start) + " ns");

        // Test PriorityQueue (min-heap by default)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        start = System.nanoTime();
        for (int val : data) {
            pq.offer(val);
        }
        end = System.nanoTime();
        System.out.println("PriorityQueue insert time: " + (end - start) + " ns");

        start = System.nanoTime();
        while (!pq.isEmpty()) {
            pq.poll();
        }
        end = System.nanoTime();
        System.out.println("PriorityQueue extract time: " + (end - start) + " ns");
    }

    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(size * 10);
        }
        return arr;
    }

    private static int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }
}
