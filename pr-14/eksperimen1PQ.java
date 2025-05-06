import java.util.PriorityQueue;
import java.util.Arrays;

public class eksperimen1PQ {

    // Format: {0, 4, 0, 0, 1} berarti node 0 terhubung ke node 1 (weight=4) dan node 4 (weight=1)
    // Format: {0, 4, 0, 0, 1} means node 0 is connected to node 1 (weight=4) and node 4 (weight=1)
    static int[][] smallGraph = {
        {0, 4, 0, 0, 1},  // Node 0
        {4, 0, 8, 0, 0},  // Node 1
        {0, 8, 0, 7, 0},  // Node 2
        {0, 0, 7, 0, 5},  // Node 3
        {1, 0, 0, 5, 0}   // Node 4
    };

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
        int[] result = dijkstraPQ(smallGraph, 0);
        System.out.println("Jarak terpendek dari node 0 menggunakan PriorityQueue: " + Arrays.toString(result));
        // Output: [0, 4, 12, 6, 1]
    }
}
