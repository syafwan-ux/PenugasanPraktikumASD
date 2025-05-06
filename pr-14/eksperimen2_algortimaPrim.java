import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class eksperimen2_algortimaPrim {

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

    public static List<Edge> primMST(List<Edge> edges, int nodeCount) {
        List<Edge> mst = new ArrayList<>();
        boolean[] visited = new boolean[nodeCount];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        // Mulai dari node 0
        visited[0] = true;
        for (Edge edge : edges) {
            if (edge.src == 0 || edge.dest == 0) {
                pq.add(edge);
            }
        }

        while (!pq.isEmpty() && mst.size() < nodeCount - 1) {
            Edge edge = pq.poll();
            if (visited[edge.src] && visited[edge.dest])
                continue;

            mst.add(edge);
            int newNode = visited[edge.src] ? edge.dest : edge.src;
            visited[newNode] = true;

            for (Edge e : edges) {
                if ((e.src == newNode || e.dest == newNode) && !(visited[e.src] && visited[e.dest])) {
                    pq.add(e);
                }
            }
        }
        return mst;
    }

    public static void main(String[] args) {
        eksperimen2_algortimaPrim instance = new eksperimen2_algortimaPrim();
        List<Edge> primResult = primMST(Arrays.asList(instance.sampleEdges), 5);
        System.out.println("MST (Prim):");
        primResult.forEach(e -> System.out.println(e.src + " - " + e.dest + " (Weight: " + e.weight + ")"));
    }
}
