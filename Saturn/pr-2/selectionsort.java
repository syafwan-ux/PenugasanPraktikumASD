public class selectionsort {

    static void selectSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            int min_index = i;

            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[min_index]) {
                    min_index = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[min_index];
            arr[min_index] = temp;
        }
    }


    public static void main(String[] args) {
        int[] array = {4, 3, 4, 6, 1, 2, 5};

        selectSort(array);
        
        for (int val : array) {
            System.out.println(val + " ");
        }
        System.out.println();
    }
}