import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class eksperimen2_benchmark {

    public static int[][] generateRandomGraph(int nodes, int edges) {
        int[][] graph = new int[nodes][nodes];
        Random rand = new Random();

        while (edges > 0) {
            int u = rand.nextInt(nodes);
            int v = rand.nextInt(nodes);
            if (u != v && graph[u][v] == 0) {
                int weight = 1 + rand.nextInt(10);
                graph[u][v] = weight;
                graph[v][u] = weight;
                edges--;
            }
        }
        return graph;
    }

    public static List<eksperimen2_Kruskal.Edge> convertToEdgeListForKruskal(int[][] graph) {
        List<eksperimen2_Kruskal.Edge> edges = new ArrayList<>();
        int n = graph.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (graph[i][j] != 0) {
                    edges.add(new eksperimen2_Kruskal.Edge(i, j, graph[i][j]));
                }
            }
        }
        return edges;
    }

    public static List<eksperimen2_algortimaPrim.Edge> convertToEdgeListForPrim(int[][] graph) {
        List<eksperimen2_algortimaPrim.Edge> edges = new ArrayList<>();
        int n = graph.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (graph[i][j] != 0) {
                    edges.add(new eksperimen2_algortimaPrim.Edge(i, j, graph[i][j]));
                }
            }
        }
        return edges;
    }

    public static void benchmark() {
        int[] sizes = {100, 500, 1000, 2500, 5000};
        int edgeFactor = 5; // edges = nodes * factor
        int iterations = 5;

        for (int size : sizes) {
            long totalPrimTime = 0;
            long totalKruskalTime = 0;

            for (int iter = 0; iter < iterations; iter++) {
                int[][] graph = generateRandomGraph(size, size * edgeFactor);
                List<eksperimen2_algortimaPrim.Edge> primEdges = convertToEdgeListForPrim(graph);
                List<eksperimen2_Kruskal.Edge> kruskalEdges = convertToEdgeListForKruskal(graph);

                long start = System.nanoTime();
                eksperimen2_algortimaPrim.primMST(primEdges, size);
                long primTime = (System.nanoTime() - start) / 1000000;
                totalPrimTime += primTime;

                start = System.nanoTime();
                eksperimen2_Kruskal.kruskalMST(kruskalEdges, size);
                long kruskalTime = (System.nanoTime() - start) / 1000000;
                totalKruskalTime += kruskalTime;
            }

            System.out.printf(
                "Nodes: %d | Edges: %d | Prim Avg: %d ms | Kruskal Avg: %d ms\n",
                size, size * edgeFactor, totalPrimTime / iterations, totalKruskalTime / iterations
            );
        }
    }

    public static void main(String[] args) {
        benchmark();
    }
}
