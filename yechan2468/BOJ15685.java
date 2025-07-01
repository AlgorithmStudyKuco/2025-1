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
    static final int LEFT = 1, RIGHT = -1;
    static final int[] dy = new int[]{0, -1, 0, 1};
    static final int[] dx = new int[]{1, 0, -1, 0};
    static List<Integer> histories;
    static Pos pos;
    static int dir;

    public static void main(String[] args) throws IOException {
        getInput();

        for (int i = 0; i < n; i++) {
            simulate(curves[i]);
        }

        System.out.println(countAnswer());
    }

    private static void simulate(int[] curve) {
        int startX = curve[0], startY = curve[1], startDir = curve[2], generations = curve[3];
        histories = new ArrayList<>();

        pos = new Pos(startY, startX);
        dir = startDir;
        visit(startY, startX);

        move();
        turn(LEFT);

        for (int gen = 1; gen <= generations; gen++) {
            int len = histories.size();
            for (int i = len - 2; i >= 0; i--) {
                move();
                turn(histories.get(i) == LEFT ? RIGHT : LEFT);
            }
            move();
            turn(LEFT);
        }
    }

    private static void move() {
        int ny = pos.y + dy[dir], nx = pos.x + dx[dir];
        visit(ny, nx);
        pos.y = ny; pos.x = nx;
    }

    private static void visit(int y, int x) {
        board[y][x] = true;
    }

    private static void turn(int turnDir) {
        dir = (dir + turnDir + 4) % 4;
        histories.add(turnDir);
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

    static class Pos {
        int x, y;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
