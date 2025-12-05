import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ9328 {

    private static int height, width;
    private static char[][] board;
    private static boolean[][] visited;
    private static Set<Character> keys;
    private static int answer;
    private final static char BLANK = '.', WALL = '*', TARGET = '$';
    private final static int[] dy = {-1, 0, 0, 1}, dx = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int numTestcase = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numTestcase; i++) {
            initialize(reader);

            while (true) {
                int initialSize = keys.size();

                clearVisited();
                openDoor();
                bfs();

                if (keys.size() == initialSize) break;
            }

            System.out.println(answer);
        }
    }

    private static void bfs() {
        Queue<Pos> queue = initQueue();

        while (!queue.isEmpty()) {
            Pos pos = queue.poll();

            if (visited[pos.row][pos.col]) continue;
            visited[pos.row][pos.col] = true;

            char curr = board[pos.row][pos.col];
            if (curr == TARGET) {
                answer++;
                setCellBlank(pos);
            } else if (isKey(curr)) {
                keys.add(curr);
                setCellBlank(pos);
            }

            for (int i = 0; i < 4; i++) {
                int ny = pos.row + dy[i];
                int nx = pos.col + dx[i];
                if (ny < 0 || height <= ny || nx < 0 || width <= nx) continue;
                if (visited[ny][nx]) continue;
                if (isDoor(board[ny][nx])) continue;

                queue.offer(new Pos(ny, nx));
            }
        }
    }

    private static Queue<Pos> initQueue() {
        Queue<Pos> queue = new LinkedList<>();

        for (int i = 0; i < height; i++) {
            addToQueueIfValid(queue, i, 0);
            addToQueueIfValid(queue, i, width - 1);
        }

        for (int i = 0; i < width; i++) {
            addToQueueIfValid(queue, 0, i);
            addToQueueIfValid(queue, height - 1, i);
        }

        return queue;
    }

    private static void addToQueueIfValid(Queue<Pos> queue, int row, int col) {
        if (!visited[row][col] && canEnter(row, col)) {
            queue.offer(new Pos(row, col));
        }
    }

    private static boolean canEnter(int row, int col) {
        char cell = board[row][col];
        if (cell == WALL) return false;
        if (isDoor(cell)) return false;
        return true;
    }

    private static void openDoor() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (char key : keys) {
                    if (board[i][j] == upper(key)) {
                         board[i][j] = BLANK;
                    }
                }
            }
        }
    }

    private static void initialize(BufferedReader reader) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        height = Integer.parseInt(tokenizer.nextToken());
        width = Integer.parseInt(tokenizer.nextToken());
        board = new char[height][width];

        for (int j = 0; j < height; j++) {
            String boardRow = reader.readLine();
            for (int k = 0; k < width; k++) {
                board[j][k] = boardRow.charAt(k);
            }
        }

        String keysRow = reader.readLine();
        keys = new HashSet<>();
        if (!keysRow.equals("0")) {
            for (int j = 0; j < keysRow.length(); j++) {
                keys.add(keysRow.charAt(j));
            }
        }

        clearVisited();

        answer = 0;
    }

    private static void clearVisited() {
        visited = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == WALL) {
                    visited[i][j] = true;
                }
            }
        }
    }

    private static void setCellBlank(Pos pos) {
        board[pos.row][pos.col] = BLANK;
    }

    private static boolean isKey(char x) {
        return 'a' <= x && x <= 'z';
    }

    private static boolean isDoor(char x) {
        return 'A' <= x && x <= 'Z';
    }

    private static char upper(char lowercase) {
        return (char) (lowercase + 'A' - 'a');
    }

    private static char lower(char uppercase) {
        return (char) (uppercase - 'A' + 'a');
    }

    private static class Pos {
        int row, col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
