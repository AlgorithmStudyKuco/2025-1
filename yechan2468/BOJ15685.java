import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15685 {
    static boolean[][] board;

    public static void main(String[] args) throws IOException {
        Dragon[] dragons = initialize();

        for (Dragon dragon : dragons) {
            dragon.simulate();
        }

        System.out.println(countAnswer());
    }

    static class Dragon {
        private final List<Integer> histories;
        private final Pos pos;
        private int dir;
        private final int generation;
        private final int[] dy = new int[]{0, -1, 0, 1};
        private final int[] dx = new int[]{1, 0, -1, 0};
        private final int LEFT = 1, RIGHT = -1;

        public Dragon(Pos pos, int dir, int generation) {
            histories = new ArrayList<>();
            this.pos = pos;
            this.dir = dir;
            this.generation = generation;
            this.visit();
        }

        public void simulate() {
            move();
            turn(LEFT);

            for (int gen = 1; gen <= generation; gen++) {
                int len = histories.size();
                for (int i = len - 2; i >= 0; i--) {
                    move();
                    turn(histories.get(i) == LEFT ? RIGHT : LEFT);
                }
                move();
                turn(LEFT);
            }
        }

        private void turn(int turnDir) {
            dir = (dir + turnDir + 4) % 4;
            histories.add(turnDir);
        }

        private void visit() {
            board[pos.y][pos.x] = true;
        }

        private void move() {
            pos.y += dy[dir]; pos.x += dx[dir];
            visit();
        }
    }

    private static Dragon[] initialize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        board = new boolean[101][101];
        Dragon[] dragons = new Dragon[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int startX = Integer.parseInt(tokenizer.nextToken());
            int startY = Integer.parseInt(tokenizer.nextToken());
            int startDir = Integer.parseInt(tokenizer.nextToken());
            int generation = Integer.parseInt(tokenizer.nextToken());
            dragons[i] = new Dragon(new Pos(startY, startX), startDir, generation);
        }

        return dragons;
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

    static class Pos {
        int x, y;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
