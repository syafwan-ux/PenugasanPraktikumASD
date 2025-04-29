public class HeapsortAplikasi {

	public static void heapSort(int[] arr, boolean ascending) {
        int n = arr.length;
        HeapTree heap = new HeapTree(n, ascending ? true : false); // max-heap for ascending, min-heap for descending

        // Build heap
        for (int num : arr) {
            heap.insert(num);
        }

        // Extract elements from heap to get sorted array
        for (int i = 0; i < n; i++) {
            arr[i] = heap.extractRoot();
        }
    }

    public static int[] findKElements(int[] arr, int k, boolean findLargest) {
        if (k <= 0 || k > arr.length) {
            throw new IllegalArgumentException("Invalid value of k");
        }

        // Use min-heap to find k largest elements, max-heap to find k smallest elements
        HeapTree heap = new HeapTree(k, !findLargest);

        for (int num : arr) {
            if (heap.getSize() < k) {
                heap.insert(num);
            } else {
                if (findLargest && num > heap.getRoot()) {
                    heap.replaceRoot(num);
                } else if (!findLargest && num < heap.getRoot()) {
                    heap.replaceRoot(num);
                }
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = heap.extractRoot();
        }

        // If finding largest, result is min-heap order, so reverse to descending
        if (findLargest) {
            for (int i = 0; i < k / 2; i++) {
                int temp = result[i];
                result[i] = result[k - 1 - i];
                result[k - 1 - i] = temp;
            }
        }

        return result;
    }

    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        java.util.Random rand = new java.util.Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100000); // random int between 0 and 99999
        }
        return arr;
    }

    public static void main(String[] args) {
        // Test heapSort with small array
        int[] smallArray = {12, 11, 13, 5, 6, 7};
        System.out.println("Original small array:");
        printArray(smallArray);

        heapSort(smallArray, true);
        System.out.println("Sorted small array ascending:");
        printArray(smallArray);

        heapSort(smallArray, false);
        System.out.println("Sorted small array descending:");
        printArray(smallArray);

        // Test heapSort with large array
        int[] largeArray = generateRandomArray(10000);
        System.out.println("Sorting large array of size 10000...");
        heapSort(largeArray, true);
        System.out.println("Large array sorted ascending (first 20 elements):");
        printArray(java.util.Arrays.copyOfRange(largeArray, 0, 20));

        // Test findKElements
        int k = 5;
        int[] kLargest = findKElements(smallArray, k, true);
        System.out.println("K largest elements in small array:");
        printArray(kLargest);

        int[] kSmallest = findKElements(smallArray, k, false);
        System.out.println("K smallest elements in small array:");
        printArray(kSmallest);
    }

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
