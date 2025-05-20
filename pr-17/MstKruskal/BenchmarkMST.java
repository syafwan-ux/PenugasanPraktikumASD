package MstKruskal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BenchmarkMST {

    public static class Edge extends KruskalMST_DisjointSet.Edge {
        public Edge(int src, int dest, int weight) {
            super(src, dest, weight);
        }
    }

    private static List<KruskalMST_DisjointSet.Edge> generateRandomGraph(int vertices, int edgesCount) {
        Random rand = new Random();
        List<KruskalMST_DisjointSet.Edge> edges = new ArrayList<>();
        Set<String> existingEdges = new HashSet<>();
        while (edges.size() < edgesCount) {
            int src = rand.nextInt(vertices);
            int dest = rand.nextInt(vertices);
            if (dest == src) continue;
            String edgeKey = src < dest ? src + "-" + dest : dest + "-" + src;
            if (existingEdges.contains(edgeKey)) continue;
            existingEdges.add(edgeKey);
            int weight = rand.nextInt(100) + 1;
            edges.add(new KruskalMST_DisjointSet.Edge(src, dest, weight));
        }
        return edges;
    }

    public static void benchmark(int vertices) {
        int edgesCount = vertices * 5; // mengkikuti eksperimen sebelumnya
        List<KruskalMST_DisjointSet.Edge> edges = generateRandomGraph(vertices, edgesCount);

        // Benchmark Disjoint Set Kruskal
        long startDS = System.nanoTime();
        KruskalMST_DisjointSet kruskalDS = new KruskalMST_DisjointSet(vertices);
        for (KruskalMST_DisjointSet.Edge e : edges) {
            kruskalDS.addEdge(e.src, e.dest, e.weight);
        }
        kruskalDS.getMST();
        long endDS = System.nanoTime();

        // Benchmark DFS cycle detection Kruskal
        long startDFS = System.nanoTime();
        List<KruskalMST_DFS.Edge> edgesDFS = new ArrayList<>();
        for (KruskalMST_DisjointSet.Edge e : edges) {
            edgesDFS.add(new KruskalMST_DFS.Edge(e.src, e.dest, e.weight));
        }
        List<KruskalMST_DFS.Edge> mstDFS = KruskalMST_DFS.kruskalMST(edgesDFS, vertices);
        long endDFS = System.nanoTime();

        System.out.println("Vertices: " + vertices + ", Edges: " + edgesCount);
        System.out.println("Disjoint Set Kruskal time: " + (endDS - startDS) / 1_000_000 + " ms");
        System.out.println("DFS cycle detection Kruskal time: " + (endDFS - startDFS) / 1_000_000 + " ms");
        System.out.println("MST weight (Disjoint Set): " + kruskalDS.getMSTWeight());
        int totalWeightDFS = 0;
        for (KruskalMST_DFS.Edge e : mstDFS) {
            totalWeightDFS += e.weight;
        }
        System.out.println("MST weight (DFS cycle detection): " + totalWeightDFS);
        System.out.println("=======================================");
    }

    public static void main(String[] args) {
        benchmark(100);
        benchmark(500);
        benchmark(1000);
        benchmark(2500);
        benchmark(5000);
    }
}
