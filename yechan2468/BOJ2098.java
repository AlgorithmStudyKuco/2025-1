import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2098 {
    static int n;
    static int[][] graph;
    static int[][] memo;
    static final int NO_PATH = 0, START_NODE = 0;

    public static void main(String[] args) throws IOException {
        initialize();

        solve();

        printAnswer();
    }

    private static void solve() {
        Queue<QueueElem> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x.sum));
        queue.offer(new QueueElem(START_NODE));

        while (!queue.isEmpty()) {
            QueueElem curr = queue.poll();

            for (int next = 0; next < n; next++) {
                int weight = graph[curr.nodeNo][next];
                if (weight == NO_PATH) continue;
                if (curr.isVisited(next) || (next == START_NODE && curr.getDepth() < n - 1)) continue;

                int newVisited = curr.visited | andVisit(next);
                int newSum = curr.sum + weight;

                if (newSum < memo[next][newVisited]) {
                    memo[next][newVisited] = newSum;
                    queue.offer(new QueueElem(next, newVisited, newSum));
                }
            }
        }
    }

    private static void printAnswer() {
        int answer = Integer.MAX_VALUE;
        int visitedAll = 0;
        for (int i = 0; i < n; i++) {
            visitedAll |= andVisit(i);
        }
        for (int i = 0; i < n; i++) {
            answer = Math.min(answer, memo[i][visitedAll]);
        }

        System.out.println(answer);
    }

    private static void initialize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        memo = new int[n][0x10000];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        memo[0][1] = 0;
    }

    private static int andVisit(int nodeNo) {
        return 1 << nodeNo;
    }

    private static class QueueElem {
        int nodeNo, visited, sum;

        public QueueElem(int nodeNo, int visited, int sum) {
            this.nodeNo = nodeNo;
            this.visited = visited;
            this.sum = sum;
        }

        public QueueElem(int nodeNo) {
            this(nodeNo, 0, 0);
        }

        public boolean isVisited(int nodeNo) {
            return (visited & (1 << nodeNo)) != 0;
        }

        public int getDepth() {
            return Integer.bitCount(visited);
        }
    }
}
