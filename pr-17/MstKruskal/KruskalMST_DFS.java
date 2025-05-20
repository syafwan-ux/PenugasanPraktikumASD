package MstKruskal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KruskalMST_DFS {

    public static class Edge {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

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
}
