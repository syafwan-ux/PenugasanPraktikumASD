public class bubblesort {

    static void bubSort(int[] array) {
        int temp;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            boolean swapped = false;
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j+1]) {
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;

                    swapped = true;
                }
            }
            if (swapped == false) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {4, 3, 4, 6, 1, 2, 5};
        bubSort(array);

        for (int val : array) {
            System.out.println(val);
        }
 
    }
}
