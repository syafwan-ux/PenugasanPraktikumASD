import java.util.Arrays;
class ArrayCopyTest {
    public static void main(String[] args) {
        int[] arrAsli = {1, 2, 3, 4, 5};// Referensi langsung
        int[] arrReferensi = arrAsli;

        // Salinan manual
        int[] arrManual = new int[arrAsli.length];
        for (int i = 0; i < arrAsli.length; i++) {
            arrManual[i] = arrAsli[i];
        }

        // Salinan menggunakan clone()
        

        // Modifikasi array asli
        arrAsli[0] = 99;

        int[] arrClone = arrAsli.clone();

	  // Output
        System.out.println("Array asli: " + Arrays.toString(arrAsli));
        System.out.println("Array referensi: " + Arrays.toString(arrReferensi));
        System.out.println("Array manual: " + Arrays.toString(arrManual));
        System.out.println("Array clone: " + Arrays.toString(arrClone));
    }
}

