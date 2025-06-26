import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11657 {
    public static void main(String[] args) throws IOException {
        Initialization init = initialize();
        int v = init.v, e = init.e;
        int[][] edges = init.edges;
        long[] distances = init.distances;

        bellmanFord(v, e, edges, distances);

        if (v == 1) {
            System.out.println(0);
            return;
        }

        if (isNegativeCycleExists(e, edges, distances)) {
            System.out.println(-1);
            return;
        }

        printAnswer(v, distances);
    }


    private static void bellmanFord(int v, int e, int[][] edges, long[] distances) {
        for (int i = 0; i < v - 1; i++) {
            for (int j = 0; j < e; j++) {
                int start = edges[j][0], end = edges[j][1], weight = edges[j][2];
                if (distances[start] == Integer.MAX_VALUE) continue;
                distances[end] = Math.min(distances[end], distances[start] + weight);
            }
        }
    }

    private static boolean isNegativeCycleExists(int e, int[][] edges, long[] distances) {
        for (int j = 0; j < e; j++) {
            int start = edges[j][0], end = edges[j][1], weight = edges[j][2];
            if (distances[start] == Integer.MAX_VALUE) continue;
            if (distances[end] > distances[start] + weight) {
                return true;
            }
        }
        return false;
    }

    private static void printAnswer(int v, long[] distances) {
        for (int i = 1; i < v; i++) {
            System.out.println(distances[i] == Integer.MAX_VALUE ? -1 : distances[i]);
        }
    }

    private static Initialization initialize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int v = Integer.parseInt(tokenizer.nextToken());
        int e = Integer.parseInt(tokenizer.nextToken());
        int[][] edges = new int[e][3];
        for (int i = 0; i < e; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            edges[i][0] = Integer.parseInt(tokenizer.nextToken()) - 1;
            edges[i][1] = Integer.parseInt(tokenizer.nextToken()) - 1;
            edges[i][2] = Integer.parseInt(tokenizer.nextToken());
        }
        long[] distances = new long[v];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[0] = 0;

        return new Initialization(v, e, edges, distances);
    }

    private static class Initialization {
        public final int v;
        public final int e;
        public final int[][] edges;
        public final long[] distances;

        public Initialization(int v, int e, int[][] edges, long[] distances) {
            this.v = v;
            this.e = e;
            this.edges = edges;
            this.distances = distances;
        }
    }
}