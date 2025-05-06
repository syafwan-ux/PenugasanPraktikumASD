import java.util.Arrays;

public class eksperimen1 {

    static int[][] graph = {
        {0, 4, 0, 0, 1},  // Node 0
        {4, 0, 8, 0, 0},  // Node 1
        {0, 8, 0, 7, 0},  // Node 2
        {0, 0, 7, 0, 5},  // Node 3
        {1, 0, 0, 5, 0}   // Node 4
    };

    public static int[] dijkstraArray(int[][] graph, int start) {
        int n = graph.length;
        int[] distances = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        for (int i = 0; i < n; i++) {
            // 1. Cari node dengan jarak terpendek yang belum dikunjungi
            // 1. Find the unvisited node with the shortest distance
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

    public static void main(String[] args) {
        int[] result = dijkstraArray(graph, 0);
        System.out.println("Jarak terpendek dari node 0: " + Arrays.toString(result));
        // Output: [0, 4, 12, 6, 1]
    }
}
