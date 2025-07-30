import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ2623 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int maxNumber = Integer.parseInt(tokenizer.nextToken());
        int numArray = Integer.parseInt(tokenizer.nextToken());

        ArrayList<LinkedList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < maxNumber; i++) {
            graph.add(new LinkedList<>());
        }
        int[] indegrees = new int[maxNumber];
        boolean[] visited = new boolean[maxNumber];

        for (int i = 0; i < numArray; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int arraySize = Integer.parseInt(tokenizer.nextToken());
            int prev = Integer.parseInt(tokenizer.nextToken()) - 1;
            for (int j = 0; j < arraySize - 1; j++) {
                int curr = Integer.parseInt(tokenizer.nextToken()) - 1;
                graph.get(prev).add(curr);
                indegrees[curr]++;
                prev = curr;
            }
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int cnt = 0;
        LOOP: while (cnt < maxNumber) {
            for (int i = 0; i < maxNumber; i++) {
                if (!visited[i] && indegrees[i] == 0) {
                    visited[i] = true;
                    cnt++;
                    writer.write((i + 1) + "\n");
                    for (int neighbor : graph.get(i)) {
                        indegrees[neighbor]--;
                    }
                    continue LOOP;
                }
            }
            break;
        }

        if (cnt < maxNumber) {
            System.out.println(0);
        } else {
            writer.flush();
        }
    }
}
