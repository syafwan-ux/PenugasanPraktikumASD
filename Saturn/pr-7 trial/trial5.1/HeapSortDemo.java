import java.util.Arrays;

public class HeapSortDemo {
    public static void heapSort(int[] arr) {
        int len = arr.length;

        for (int i = len / 2 - 1; i >= 0; i--) { // last non-leaf node
            heapify(arr, len, i); // heapify the array
        }
    }

    static void heapify(int[] arr, int len, int i) {
        int max = i; // i = 0
        int left = 2 * i + 1; // len is the length of the array
        int right = 2 * i + 2;

        if (left < len && arr[left] > arr[max]) { // left < len is to check if the array is correct
            max = left; // if left child of the last leaf is greater than the parent, swap
        }
        if (right < len && arr[right] > arr[max]) { // same for the right
            max = right;
        }
        if (max != i) {
            swap(arr, i, max);
            heapify(arr, len, max);
        }
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {30,55,2,20,86,48,74,89,12,84,73,77,61,38,82};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    // overall conclusions
    // set the heapSort for sorting and heapify for the heap special sorting
}
