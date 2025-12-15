import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2887 {
    private static int n;
    private static Node[][] coordinates;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        initialize();

        List<Delta> edges = getEdges();

        int answer = prim(edges);

        System.out.println(answer);
    }

    private static List<Delta> getEdges() {
        for (Node[] axisValues : coordinates) {
            Arrays.sort(axisValues, Comparator.comparingInt(node -> node.value));
        }

        List<Delta> edges = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            for (Node[] axisValues : coordinates) {
                Node prev = axisValues[i - 1], curr = axisValues[i];
                Delta delta = new Delta(Math.abs(prev.value - curr.value), prev.nodeNo, curr.nodeNo);
                edges.add(delta);
            }
        }
        edges.sort(Comparator.comparingInt(delta -> delta.deltaValue));
        return edges;
    }

    private static int prim(List<Delta> edges) {
        int answer = 0;
        for (Delta delta : edges) {
            if (find(delta.node1No) == find(delta.node2No)) {
                continue;
            }
            union(delta.node1No, delta.node2No);
            answer += delta.deltaValue;
        }
        return answer;
    }

    private static void initialize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        coordinates = new Node[3][n];
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (Node[] axis : coordinates) {
                axis[i] = new Node(Integer.parseInt(tokenizer.nextToken()), i);
            }
        }
    }

    private static class Node {
        int value;
        int nodeNo;

        public Node(int value, int nodeNo) {
            this.value = value;
            this.nodeNo = nodeNo;
        }
    }

    private static class Delta {
        int deltaValue;
        int node1No;
        int node2No;

        public Delta(int deltaValue, int node1No, int node2No) {
            this.deltaValue = deltaValue;
            this.node1No = node1No;
            this.node2No = node2No;
        }
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        parents[a] = b;
    }

    private static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }
}
