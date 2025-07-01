import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1613 {
    static int v, numQuery;
    static int[][] graph, queries;
    static final int INF = 500;

    public static void main(String[] args) throws IOException {
        initialize();

        for (int k = 0; k < v; k++) {
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < v; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < numQuery; i++) {
            int start = queries[i][0], end = queries[i][1];
            writer.write((graph[start][end] != INF ? "-1" : (graph[end][start] != INF ? "1" : "0")) + "\n");
        }
        writer.flush();
    }

    private static void initialize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        v = Integer.parseInt(tokenizer.nextToken());
        int e = Integer.parseInt(tokenizer.nextToken());
        graph = new int[v][v];
        for (int i = 0; i < v; i++) {
            Arrays.fill(graph[i], INF);
        }
        for (int i = 0; i < v; i++) {
            graph[i][i] = 0;
        }
        for (int i = 0; i < e; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken()) - 1;
            int end = Integer.parseInt(tokenizer.nextToken()) - 1;
            graph[start][end] = 1;
        }
        numQuery = Integer.parseInt(reader.readLine());
        queries = new int[numQuery][2];
        for (int i = 0; i < numQuery; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            queries[i][0] = Integer.parseInt(tokenizer.nextToken()) - 1;
            queries[i][1] = Integer.parseInt(tokenizer.nextToken()) - 1;
        }
    }
}