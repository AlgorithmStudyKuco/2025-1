import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2468 {
    static int n, answer;
    static int[][] heights;
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        heights = new int[n + 2][n + 2];
        for (int i = 1; i <= n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= n; j++) {
                heights[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        for (int level = 0; level < 100; level++) {
            int count = 0;
            boolean[][] isSunk = new boolean[n+2][n+2];
            for (int i = 0; i < n+2; i++) {
                isSunk[i][0] = isSunk[i][n+1] = isSunk[0][i] = isSunk[n+1][i] = true;
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (heights[i][j] <= level) {
                        isSunk[i][j] = true;
                    }
                }
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (!isSunk[i][j] && heights[i][j] > level) {
                        bfs(new Pair(i, j), level, isSunk);
                        count++;
                    }
                }
            }

            answer = Math.max(answer, count);
        }

        System.out.println(answer);
    }

    static void bfs(Pair curr, int level, boolean[][] visited) {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(curr);
        visited[curr.y][curr.x] = true;
        while (!queue.isEmpty()) {
            Pair next = queue.poll();
            for (int i = 0; i < 4; i++) {
                int ny = next.y + dy[i];
                int nx = next.x + dx[i];
                if (visited[ny][nx] || heights[ny][nx] <= level) continue;
                bfs(new Pair(ny, nx), level, visited);
            }
        }
    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}