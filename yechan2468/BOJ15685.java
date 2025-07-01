import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15685 {
    static int n;
    static int[][] curves;
    static boolean[][] board;
    static final int TURN_LEFT = 1, TURN_RIGHT = -1;
    static final int[] dy = new int[]{0, -1, 0, 1};
    static final int[] dx = new int[]{1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        getInput();

        for (int i = 0; i < n; i++) {
            simulate(curves[i]);
        }

        System.out.println(countAnswer());
    }

    private static void simulate(int[] curve) {
        int originX = curve[0], originY = curve[1], originDir = curve[2], originGen = curve[3];
        List<Integer> histories = new ArrayList<>();

        int x = originX, y = originY, dir = originDir;
        board[originY][originX] = true;
        y += dy[dir]; x += dx[dir];
        board[y][x] = true;
        dir = turnLeft(dir, histories);
        if (originGen == 0) return;

        y += dy[dir]; x += dx[dir];
        board[y][x] = true;
        dir = turnLeft(dir, histories);
        if (originGen == 1) return;

        for (int gen = 2; gen <= originGen; gen++) {
            int len = histories.size();
            for (int i = len - 2; i >= 0; i--) {
                y += dy[dir]; x += dx[dir];
                board[y][x] = true;
                int history = histories.get(i);
                if (history == TURN_LEFT) {
                    dir = turnRight(dir, histories);
                } else {
                    dir = turnLeft(dir, histories);
                }
            }
            y += dy[dir]; x += dx[dir];
            board[y][x] = true;
            dir = turnLeft(dir, histories);
        }
    }

    private static int turnLeft(int dir, List<Integer> histories) {
        dir += 4 + TURN_LEFT;
        dir %= 4;
        histories.add(TURN_LEFT);
        return dir;
    }

    private static int turnRight(int dir, List<Integer> histories) {
        dir += 4 + TURN_RIGHT;
        dir %= 4;
        histories.add(TURN_RIGHT);
        return dir;
    }

    private static int countAnswer() {
        int result = 0;
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board.length; j++) {
                if (board[i][j] && board[i - 1][j] && board[i][j - 1] && board[i - 1][j - 1]) {
                    result++;
                }
            }
        }

        return result;
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        curves = new int[n][4];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < 4; j++) {
                curves[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        board = new boolean[101][101];
    }
}
