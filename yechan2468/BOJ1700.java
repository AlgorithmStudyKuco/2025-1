import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1700 {
    static int numOutlet, numUsage;
    static int[] order;
    static int answer = 0;
    static final int INF = 101;

    public static void main(String[] args) throws IOException {
        getInput();

        int[][] counts = countNearest();

        Set<Integer> currSet = new HashSet<>();
        for (int i = 0; i < numUsage; i++) {
            if (currSet.contains(order[i])) continue;
            
            if (currSet.size() == numOutlet) {
                int[] currCount = counts[i];
                PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> Integer.compare(currCount[b], currCount[a]));
                queue.addAll(currSet);
                currSet.remove(queue.poll());
                answer++;
            }

            currSet.add(order[i]);
        }

        System.out.println(answer);
    }

    private static int[][] countNearest() {
        int[][] counts = new int[numUsage][numUsage];
        for (int i = 0; i < numUsage; i++) {
            Arrays.fill(counts[i], INF);
        }
        for (int i = numUsage - 1; i >= 0; i--) {
            if (i == numUsage - 1) continue;
            for (int j = 0; j < numUsage; j++) {
                counts[i][j] = counts[i+1][j] + 1;
            }
            counts[i][order[i]] = 0;
        }
        return counts;
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        numOutlet = Integer.parseInt(tokenizer.nextToken());
        numUsage = Integer.parseInt(tokenizer.nextToken());
        order = new int[numUsage];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < numUsage; i++) {
            order[i] = Integer.parseInt(tokenizer.nextToken()) - 1;
        }
    }
}
