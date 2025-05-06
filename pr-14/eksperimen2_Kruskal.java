import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class eksperimen2_Kruskal {

    static class Edge {
        int src;
        int dest;
        int weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    Edge[] sampleEdges = {
        new Edge(0, 1, 4),
        new Edge(0, 4, 1),
        new Edge(1, 2, 8),
        new Edge(2, 3, 7),
        new Edge(3, 4, 5),
        new Edge(1, 4, 2)  // Edge tambahan untuk alternatif MST
    };

    public static List<Edge> kruskalMST(List<Edge> edges, int nodeCount) {
        List<Edge> mst = new ArrayList<>();
        edges.sort(Comparator.comparingInt(e -> e.weight));

        for (Edge edge : edges) {
            mst.add(edge);
            if (hasCycle(mst, nodeCount)) {
                mst.remove(mst.size() - 1);
            }
            if (mst.size() == nodeCount - 1) break;
        }
        return mst;
    }

    private static boolean hasCycle(List<Edge> edges, int nodeCount) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < nodeCount; i++) {
            adj.add(new ArrayList<>());
        }
        for (Edge e : edges) {
            adj.get(e.src).add(e.dest);
            adj.get(e.dest).add(e.src);
        }

        boolean[] visited = new boolean[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            if (!visited[i] && dfsCycle(adj, visited, i, -1)) {
                return true;
            }
        }
        return false;
    }

    private static boolean dfsCycle(List<List<Integer>> adj, boolean[] visited, int u, int parent) {
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                if (dfsCycle(adj, visited, v, u)) return true;
            } else if (v != parent) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        eksperimen2_Kruskal instance = new eksperimen2_Kruskal();
        List<Edge> kruskalResult = kruskalMST(Arrays.asList(instance.sampleEdges), 5);
        System.out.println("MST (Kruskal):");
        kruskalResult.forEach(e -> System.out.println(e.src + " - " + e.dest + " (Weight: " + e.weight + ")"));
        // Output harusnya sama dengan Prim
    }
}
