package MstKruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import UnionbyRank.UnionRank;

public class KruskalMST_DisjointSet {

    public static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    private int vertices;
    private List<Edge> edges;

    public KruskalMST_DisjointSet(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }

    public List<Edge> getMST() {
        Collections.sort(edges);
        UnionRank ds = new UnionRank(vertices);
        List<Edge> mst = new ArrayList<>();

        for (Edge edge : edges) {
            int rootSrc = ds.find(edge.src);
            int rootDest = ds.find(edge.dest);

            if (rootSrc != rootDest) {
                mst.add(edge);
                ds.union(rootSrc, rootDest);
            }
        }
        return mst;
    }

    public int getMSTWeight() {
        List<Edge> mst = getMST();
        int totalWeight = 0;
        for (Edge edge : mst) {
            totalWeight += edge.weight;
        }
        return totalWeight;
    }
}
