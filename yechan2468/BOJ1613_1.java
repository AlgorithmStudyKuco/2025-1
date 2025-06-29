import java.io.*;
import java.util.*;

public class BOJ1613_1 {
    static int v, numQuery;
    static List<List<Integer>> graph;
    static BitSet[] parents;
    static int[][] queries;
    static int[] indegrees;

    public static void main(String[] args) throws IOException {
        initialize();

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (indegrees[i] == 0) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : graph.get(curr)) {
                indegrees[next]--;
                parents[next].or(parents[curr]);
                if (indegrees[next] == 0) queue.offer(next);
            }
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int[] query : queries) {
            int start = query[0], end = query[1];
            String answer = (parents[end].get(start) ? "-1" : (parents[start].get(end) ? "1" : "0")) + "\n";
            writer.write(answer);
        }
        writer.flush();
    }


    private static void initialize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        v = Integer.parseInt(tokenizer.nextToken());
        int e = Integer.parseInt(tokenizer.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }
        indegrees = new int[v];
        for (int i = 0; i < e; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken()) - 1;
            int end = Integer.parseInt(tokenizer.nextToken()) - 1;
            graph.get(start).add(end);
            indegrees[end]++;
        }

        parents = new BitSet[v];
        for (int i = 0; i < v; i++) {
            parents[i] = new BitSet(v);
            parents[i].set(i);
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