

import java.util.Random;

public class Benchmark {

    public void N(int size) {
        Naive input = new Naive(size);
        Random rand = new Random();

        long totalFind = 0;
        long totalUnion = 0;

        for (int i = 0; i < 100; i++) {
            int a = rand.nextInt(size);
            int b = rand.nextInt(size);

            long startFind = System.nanoTime();
            input.find(a);
            long endFind = System.nanoTime();
            totalFind += (endFind - startFind);

            long startUnion = System.nanoTime();
            input.union(a, b);
            long endUnion = System.nanoTime();
            totalUnion += (endUnion - startUnion);
        }
        System.out.println("Waktu rata-rata untuk find: " + totalFind / 100 + " ns");
        System.out.println("Waktu rata-rata untuk union: " + totalUnion / 100 + " ns");
    }

    
}
