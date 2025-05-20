package UnionbyRank;

public class Main {
    public static void main(String[] args) {
        Benchmark benchmark = new Benchmark();

        System.out.println("N elemen 10");
        benchmark.N(10);
        System.out.println("==============");
        System.out.println("N elemen 100");
        benchmark.N(100);
        System.out.println("==============");
        System.out.println("N elemen 1000");
        benchmark.N(1000);
        System.out.println("==============");
        System.out.println("N elemen 10000");
        benchmark.N(10000);

    }

}
