import java.io.*;
import java.util.*;

public class BOJ9934 {
    static int n, k;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(reader.readLine());
        n = (2 << (k - 1)) - 1;
        int[] orders = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            orders[i] = Integer.parseInt(tokenizer.nextToken()) - 1;
        }
        int[] graph = new int[n];

        traverse(0, orders, graph);

        System.out.println(graph[0] + 1);
        for (int i = 2; i < n; i *= 2) {
            for (int j = i - 1; j < i * 2 - 1; j++) {
                System.out.print(graph[j] + 1 + " ");
            }
            System.out.println();
        }
    }

    private static void traverse(int curr, int[] orders, int[] graph) {
        if (curr >= n) return;

        traverse(curr * 2 + 1, orders, graph);

        graph[curr] = orders[count++];

        traverse(curr * 2 + 2, orders, graph);
    }
}
