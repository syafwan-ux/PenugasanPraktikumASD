import java.util.ArrayList;

class MemoryUsageTest {
    public static void main(String[] args) {
        long startTime, endTime;
        
        // Array Primitif
        startTime = System.nanoTime();
        int[] angkaArray = new int[1000000]; // 1 juta elemen
        for (int i = 0; i < angkaArray.length; i++) {
            angkaArray[i] = i;
        }
        endTime = System.nanoTime();
        System.out.println("Runtime array: "+(endTime - startTime)+" ns");

        // ArrayList
        startTime = System.nanoTime();
        ArrayList<Integer> angkaList = new ArrayList<>(1000000);
        for (int i = 0; i < 1000000; i++) {
            angkaList.add(i);
        }
        endTime = System.nanoTime();
        System.out.println("Runtime ArrayList: "+(endTime - startTime)+" ns");
    }
}
