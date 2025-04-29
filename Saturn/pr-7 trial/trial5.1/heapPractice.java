import java.util.Arrays;

public class heapPractice {

    public static void heapSort(int[] arr) {
        int len = arr.length;

        for (int i = len / 2 - 1; i >= 0; i--) {
            heapify(arr, len, i); 
        }
        for (int i = len - 1; i >= 0; i--) {
            swap(arr, i, 0);
            heapify(arr, i, 0);
        }
    }

    static void heapify (int[] arr, int len, int i) {
        int max = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < len && arr[left] > arr[max]) {
            max = left;
        }
        if (right < len && arr[right] > arr[max]) {
            max = right; // become the index
        }

        if (max != i) {
            swap(arr, max, i); //the swap does not affect the method below
            heapify(arr, len, max);
        }
    }

    static void swap (int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main (String[] args) {
        int[] arr = {19, 1, 18, 20, 11, 1, 1, 4, 3};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
 }
}