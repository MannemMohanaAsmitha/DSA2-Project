import java.util.*;

class Edge {
    int u, v, weight;

    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.weight = w;
    }
}

public class co4bellmanford {

    static final int INF = 999999;

    static void printDistances(int[] dist, String[] names, int iter) {
        System.out.println("Iteration " + iter + ":");

        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == INF)
                System.out.println(names[i] + " = INF");
            else
                System.out.println(names[i] + " = " + dist[i]);
        }

        System.out.println();
    }

    static void bellmanFord(int n, List<Edge> edges, int source, String[] names) {
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[source] = 0;

        System.out.println("Bellman-Ford Shortest Path Process\n");

        for (int iter = 1; iter <= n - 1; iter++) {

            boolean changed = false;

            for (Edge e : edges) {
                if (dist[e.u] != INF && dist[e.u] + e.weight < dist[e.v]) {
                    dist[e.v] = dist[e.u] + e.weight;
                    changed = true;
                }
            }

            printDistances(dist, names, iter);

            if (!changed) {
                System.out.println("No change in this iteration.");
                System.out.println("Algorithm converged early.\n");
                break;
            }
        }

        for (Edge e : edges) {
            if (dist[e.u] != INF && dist[e.u] + e.weight < dist[e.v]) {
                System.out.println("Negative cycle detected!");
                return;
            }
        }

        System.out.println("Final Shortest Distances from MJC:");
        for (int i = 0; i < n; i++) {
            System.out.println("MJC -> " + names[i] + " = " + dist[i] + " minutes");
        }

        System.out.println("\nTime Complexity:");
        System.out.println("Bellman-Ford: O(V * E)");
        System.out.println("For V = 7 and E = 11");
        System.out.println("Operations = 7 * 11 = 77 approx");
    }

    public static void main(String[] args) {

        String[] names = {"MJC", "KEM", "JAY", "KOR", "WHF", "HBR", "MRT"};

        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0, 1, 8));    // MJC -> KEM
        edges.add(new Edge(0, 2, 5));    // MJC -> JAY
        edges.add(new Edge(1, 3, 12));   // KEM -> KOR
        edges.add(new Edge(2, 3, 7));    // JAY -> KOR
        edges.add(new Edge(3, 4, 10));   // KOR -> WHF
        edges.add(new Edge(1, 5, 4));    // KEM -> HBR
        edges.add(new Edge(5, 4, 9));    // HBR -> WHF
        edges.add(new Edge(2, 5, 6));    // JAY -> HBR
        edges.add(new Edge(3, 6, 3));    // KOR -> MRT
        edges.add(new Edge(4, 6, -3));   // WHF -> MRT
        edges.add(new Edge(5, 6, 11));   // HBR -> MRT

        bellmanFord(7, edges, 0, names);
    }
}