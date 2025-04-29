class StringPerformanceTest {
    public static void main(String[] args) {
        long startTime, endTime;

        // Menggunakan String
        String teks = "";
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            teks += "-";
        }
        endTime = System.nanoTime();
        System.out.println("Runtime String: " + (endTime - startTime) + " ns");

        // Menggunakan StringBuilder
        StringBuilder sb = new StringBuilder();
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            sb.append("-");
        }
        endTime = System.nanoTime();
        System.out.println("Runtime StringBuilder: " + (endTime - startTime) + " ns");
    }
}