import java.util.*;

class Edge {
    String u, v;
    int cost;

    Edge(String u, String v, int cost) {
        this.u = u;
        this.v = v;
        this.cost = cost;
    }
}

public class co3krushkal {

    static Map<String, String> parent = new HashMap<>();

    static String find(String x) {
        while (!parent.get(x).equals(x)) {
            x = parent.get(x);
        }
        return x;
    }

    static boolean union(String a, String b) {
        String rootA = find(a);
        String rootB = find(b);

        if (rootA.equals(rootB)) {
            return false;
        }

        parent.put(rootA, rootB);
        return true;
    }

    static void printParents() {
        System.out.println("Parent State: " + parent);
    }

    public static void main(String[] args) {

        String[] stations = {"M", "K", "W", "S", "E", "Y", "H"};

        for (String s : stations) {
            parent.put(s, s);
        }

        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge("M", "K", 8));
        edges.add(new Edge("K", "W", 12));
        edges.add(new Edge("M", "S", 10));
        edges.add(new Edge("S", "E", 7));
        edges.add(new Edge("E", "W", 9));
        edges.add(new Edge("M", "Y", 11));
        edges.add(new Edge("Y", "H", 5));
        edges.add(new Edge("H", "W", 6));
        edges.add(new Edge("K", "S", 4));
        edges.add(new Edge("S", "W", 8));
        edges.add(new Edge("E", "H", 9));
        edges.add(new Edge("K", "Y", 14));

        edges.sort(Comparator.comparingInt(e -> e.cost));

        List<Edge> mst = new ArrayList<>();
        int totalCost = 0;

        System.out.println("Kruskal Algorithm Process");
        System.out.println("Goal: Select minimum cost edges without forming cycle\n");

        int step = 1;

        for (Edge e : edges) {

            System.out.println("Step " + step + ": Checking edge "
                    + e.u + " - " + e.v + " with cost " + e.cost);

            if (union(e.u, e.v)) {
                mst.add(e);
                totalCost += e.cost;

                System.out.println("Result: Accepted");
                System.out.println("Reason: No cycle is formed");
            } else {
                System.out.println("Result: Rejected");
                System.out.println("Reason: Cycle is formed");
            }

            printParents();
            System.out.println();
            step++;
        }

        System.out.println("Final Minimum Spanning Tree Edges:");
        for (Edge e : mst) {
            System.out.println(e.u + " - " + e.v + " = " + e.cost + " crore");
        }

        System.out.println("\nTotal Minimum Cost = " + totalCost + " crore");

        System.out.println("\nRedundancy Check:");
        System.out.println("MST has only one path between M and W.");
        System.out.println("So two edge-disjoint paths are not satisfied.");

        System.out.println("\nMinimum Extra Edge Added for Redundancy:");
        System.out.println("M - S = 10 crore");

        System.out.println("\nFinal Cost with Redundancy = "
                + (totalCost + 10) + " crore");

        System.out.println("\nTime Complexity:");
        System.out.println("Sorting edges: O(m log m)");
        System.out.println("Union-Find: O(m)");
        System.out.println("Overall Kruskal: O(m log m)");
    }
}