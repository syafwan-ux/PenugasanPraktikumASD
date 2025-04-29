class ArrayReference {
    public static void ubahArray(final int[] arr) {
        arr[0] = 99; 
    }

    public static void main(String[] args) {
        final int[] angka = {1, 2, 3, 4, 5};
        angka[0] = new final int[1];
        System.out.println("Sebelum perubahan: " + angka[0]);

        ubahArray(angka);
        System.out.println("Setelah perubahan: " + angka[0]); 
    }
}
//Analisis 1

// dalam metode ini, angka dalam indeks 0, bernilai 1, 
// diubah nilainya menggunakan metode bernama "ubahArray" menjadi angka 99 


// Analisis 2
//