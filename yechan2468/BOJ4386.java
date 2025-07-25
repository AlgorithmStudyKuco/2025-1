import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ4386 {
    public static void main(String[] args) throws IOException {
        final Input input = readInputs();
        final int n = input.n;
        final ArrayList<Edge> edges = input.edges;
        final DisjointSet disjointSet = new DisjointSet(n);

        final double answer = kruskal(edges, disjointSet);

        System.out.printf("%.2f\n", answer);
    }

    private static double kruskal(ArrayList<Edge> edges, DisjointSet disjointSet) {
        edges.sort(Comparator.comparingDouble(e -> e.distance));

        double answer = 0;
        for (Edge edge : edges) {
            final int a = edge.a, b = edge.b;
            if (disjointSet.find(a) == disjointSet.find(b)) continue;
            disjointSet.union(a, b);
            answer += edge.distance;
        }

        return answer;
    }

    private static Input readInputs() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(reader.readLine());
        final double[] x = new double[n];
        final double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            x[i] = Double.parseDouble(tokenizer.nextToken());
            y[i] = Double.parseDouble(tokenizer.nextToken());
        }

        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double distance = Math.sqrt(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
                edges.add(new Edge(i, j, distance));
            }
        }

        return new Input(n, edges);
    }

    private static class Input {
        final int n;
        final ArrayList<Edge> edges;

        public Input(int n, ArrayList<Edge> edges) {
            this.n = n;
            this.edges = edges;
        }
    }

    private static class DisjointSet {
        private final int[] parents;

        public DisjointSet(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public void union(int x, int y) {
            x = find(x); y = find(y);
            if (x == y) return;
            parents[x] = y;
        }

        public int find(int x) {
            if (x == parents[x]) return x;
            return parents[x] = find(parents[x]);
        }
    }

    private static class Edge {
        final int a, b;
        final double distance;

        public Edge(int a, int b, double distance) {
            this.a = a;
            this.b = b;
            this.distance = distance;
        }
    }
}
