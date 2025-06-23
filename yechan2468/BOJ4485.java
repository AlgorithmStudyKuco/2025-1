import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ4485 {
    static BufferedReader reader;
    static final int[] dy = new int[]{-1, 0, 0, 1};
    static final int[] dx = new int[]{0, -1, 1, 0};
    static int problemCount = 1;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int n = Integer.parseInt(reader.readLine());
            if (n == 0) break;
            System.out.println("Problem " + problemCount++ + ": " + dijkstra(n));
        }
    }

    static int dijkstra(int n) throws IOException {
        Init init = getInit(n);
        PriorityQueue<int[]> queue = init.queue;
        int[][] board = init.board;
        int[][] minSum = init.minSum;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0], column = curr[1], sum = curr[2];

            if (minSum[row][column] < sum) continue;

            if (row == n - 1 && column == n - 1) return sum;

            for (int i = 0; i < 4; i++) {
                int ny = row + dy[i], nx = column + dx[i];
                if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue;
                int nextSum = sum + board[ny][nx];
                if (nextSum < minSum[ny][nx])  {
                    minSum[ny][nx] = nextSum;
                    queue.offer(new int[]{ny, nx, nextSum});
                }
            }
        }

        return -1;
    }

    private static Init getInit(int n) throws IOException {
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x[2]));
        queue.offer(new int[]{0, 0, board[0][0]});

        int[][] minSum = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(minSum[i], Integer.MAX_VALUE);
        }
        minSum[0][0] = board[0][0];

        return new Init(board, queue, minSum);
    }

    private static class Init {
        public final int[][] board;
        public final PriorityQueue<int[]> queue;
        public final int[][] minSum;

        public Init(int[][] board, PriorityQueue<int[]> queue, int[][] minSum) {
            this.board = board;
            this.queue = queue;
            this.minSum = minSum;
        }
    }
}
