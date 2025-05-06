import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class eksperimen1_Benchmark {

    // Generate a random weighted graph represented as an adjacency matrix
    // with weights between 1 and 10, and 0 meaning no edge.
    public static int[][] generateRandomGraph(int size, double density) {
        Random rand = new Random();
        int[][] graph = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (rand.nextDouble() < density) {
                    int weight = rand.nextInt(10) + 1;
                    graph[i][j] = weight;
                    graph[j][i] = weight;
                }
            }
        }
        return graph;
    }

    // Dijkstra using array approach (from eksperimen1.java)
    public static int[] dijkstraArray(int[][] graph, int start) {
        int n = graph.length;
        int[] distances = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        for (int i = 0; i < n; i++) {
            int u = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (u == -1 || distances[j] < distances[u])) {
                    u = j;
                }
            }
            if (distances[u] == Integer.MAX_VALUE) break;
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !visited[v]) {
                    distances[v] = Math.min(distances[v], distances[u] + graph[u][v]);
                }
            }
        }
        return distances;
    }

    // Dijkstra using priority queue approach (from eksperimen1PQ.java)
    public static int[] dijkstraPQ(int[][] graph, int start) {
        int n = graph.length;
        int[] distances = new int[n];
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];

            if (visited[u]) continue;
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !visited[v]) {
                    int newDist = distances[u] + graph[u][v];
                    if (newDist < distances[v]) {
                        distances[v] = newDist;
                        pq.offer(new int[]{v, newDist});
                    }
                }
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        int[] sizes = {50, 100, 500, 1000, 2000, 5000};
        int iterations = 5;
        double density = 0.1; // 10% edges to keep graphs sparse

        System.out.println("Benchmarking Dijkstra Array vs PriorityQueue");
        System.out.println("GraphSize,ArrayTime(ms),PQTime(ms)");

        for (int size : sizes) {
            long totalArrayTime = 0;
            long totalPQTime = 0;

            for (int i = 0; i < iterations; i++) {
                int[][] graph = generateRandomGraph(size, density);

                long startTime = System.currentTimeMillis();
                dijkstraArray(graph, 0);
                long endTime = System.currentTimeMillis();
                totalArrayTime += (endTime - startTime);

                startTime = System.currentTimeMillis();
                dijkstraPQ(graph, 0);
                endTime = System.currentTimeMillis();
                totalPQTime += (endTime - startTime);
            }

            long avgArrayTime = totalArrayTime / iterations;
            long avgPQTime = totalPQTime / iterations;

            System.out.printf("%d,%d,%d%n", size, avgArrayTime, avgPQTime);
        }

        System.out.println("Benchmark completed. Use the output data to create performance graphs.");
    }
}
