import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2887 {
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Node[] x = new Node[n];
        Node[] y = new Node[n];
        Node[] z = new Node[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            x[i] = new Node(Integer.parseInt(tokenizer.nextToken()), i);
            y[i] = new Node(Integer.parseInt(tokenizer.nextToken()), i);
            z[i] = new Node(Integer.parseInt(tokenizer.nextToken()), i);
        }
        Arrays.sort(x, Comparator.comparingInt(node -> node.value));
        Arrays.sort(y, Comparator.comparingInt(node -> node.value));
        Arrays.sort(z, Comparator.comparingInt(node -> node.value));
        List<Delta> deltas = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            Node prevX = x[i - 1], currX = x[i];
            Node prevY = y[i - 1], currY = y[i];
            Node prevZ = z[i - 1], currZ= z[i];
            Delta deltaX = new Delta(Math.abs(prevX.value - currX.value), prevX.nodeNo, currX.nodeNo);
            Delta deltaY = new Delta(Math.abs(prevY.value - currY.value), prevY.nodeNo, currY.nodeNo);
            Delta deltaZ = new Delta(Math.abs(prevZ.value - currZ.value), prevZ.nodeNo, currZ.nodeNo);
            deltas.add(deltaX);
            deltas.add(deltaY);
            deltas.add(deltaZ);
        }
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        int answer = 0;
        deltas.sort(Comparator.comparingInt(delta -> delta.deltaValue));
        for (Delta delta : deltas) {
            if (find(delta.node1No) == find(delta.node2No)) {
                continue;
            }
            union(delta.node1No, delta.node2No);
            answer += delta.deltaValue;
        }

        System.out.println(answer);
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
