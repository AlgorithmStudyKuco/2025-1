import java.io.*;
import java.util.*;

public class BOJ1766 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int numProblem = Integer.parseInt(tokenizer.nextToken());
        int numPrerequisite = Integer.parseInt(tokenizer.nextToken());
        Map<Integer, List<Integer>> edges = new HashMap<>();
        int[] indegrees = new int[numProblem];
        for (int i = 0; i < numPrerequisite; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int src = Integer.parseInt(tokenizer.nextToken()) - 1;
            int dest = Integer.parseInt(tokenizer.nextToken()) - 1;
            edges.putIfAbsent(src, new ArrayList<>());
            edges.get(src).add(dest);
            indegrees[dest]++;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < numProblem; i++) {
            if (indegrees[i] == 0) {
                queue.add(i);
            }
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        while (!queue.isEmpty()) {
            Integer polled = queue.poll();
            writer.write((polled + 1) + " ");
            for (Integer dest : edges.getOrDefault(polled, new ArrayList<>())) {
                indegrees[dest]--;
                if (indegrees[dest] == 0) {
                    queue.add(dest);
                }
            }
        }
        writer.flush();
    }
}
