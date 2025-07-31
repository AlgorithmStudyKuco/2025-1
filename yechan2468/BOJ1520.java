import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1520 {
    private static int numRow, numColumn;
    private static int[][] heights, indegrees, memo;
    private static List<List<List<Point>>> graph;
    private static final int[] dy = {-1, 0, 0, 1}, dx = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        initialize();

        topologicalSort();

        int answer = memo[numRow - 1][numColumn - 1];
        System.out.println(answer);
    }

    private static void topologicalSort() {
        Queue<Point> queue = initQueue();

        while (!queue.isEmpty()) {
            Point curr = queue.poll();

            for (Point next : graph.get(curr.row).get(curr.col)) {
                indegrees[next.row][next.col]--;
                memo[next.row][next.col] += memo[curr.row][curr.col];
                if (indegrees[next.row][next.col] == 0) {
                    queue.offer(next);
                }
            }
        }
    }

    private static Queue<Point> initQueue() {
        Queue<Point> queue = new LinkedList<>();
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numColumn; col++) {
                if (indegrees[row][col] == 0) {
                    queue.offer(new Point(row, col));
                }
            }
        }
        return queue;
    }

    private static void initialize() throws IOException {
        readInput();

        graph = new ArrayList<>();
        for (int row = 0; row < numRow; row++) {
            graph.add(new ArrayList<>());
            for (int col = 0; col < numColumn; col++) {
                graph.get(row).add(new ArrayList<>());
            }
        }
        indegrees = new int[numRow][numColumn];
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numColumn; col++) {
                for (int dir = 0; dir < 4; dir++) {
                    int nRow = row + dy[dir], nCol = col + dx[dir];
                    if (nRow < 0 || nRow >= numRow || nCol < 0 || nCol >= numColumn) continue;
                    if (heights[nRow][nCol] >= heights[row][col]) continue;
                    graph.get(row).get(col).add(new Point(nRow, nCol));
                    indegrees[nRow][nCol]++;
                }
            }
        }
        memo = new int[numRow][numColumn];
        memo[0][0] = 1;
    }

    private static void readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        numRow = Integer.parseInt(tokenizer.nextToken());
        numColumn = Integer.parseInt(tokenizer.nextToken());
        heights = new int[numRow][numColumn];
        for (int row = 0; row < numRow; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < numColumn; col++) {
                heights[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
