import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15683 {
    static int rowSize, columnSize;
    static ArrayList<Cctv> cctvs = new ArrayList<>();
    static final int OCCUPIED = -1, BLANK = 0, SINGLE = 1, DUAL = 2, ORTHOGONAL = 3, TRIPLE = 4, QUAD = 5, WALL = 6;
    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {1, 0, -1, 0};
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        int[][] board = getInput();

        solve(0, board);

        System.out.println(answer);
    }

    private static void solve(int cctvId, int[][] board) {
        if (cctvId >= cctvs.size()) return;

        for (int j = 0; j < 4; j++) {
            Cctv cctv = cctvs.get(cctvId);

            int[][] newBoard = new int[rowSize][columnSize];
            for (int i = 0; i < rowSize; i++) {
                newBoard[i] = board[i].clone();
            }

            cctv.watch(newBoard);
            cctv.rotate();
            int numBlank = countBlank(newBoard);
            answer = Math.min(answer, numBlank);

            solve(cctvId + 1, newBoard);
        }
    }

    static class Cctv {
        public Pos pos;
        public int type;
        public int rotation;

        public Cctv(Pos pos, int type) {
            this.pos = pos;
            this.type = type;
            this.rotation = 0;
        }

        public void rotate() {
            this.rotation++;
            this.rotation %= 4;
        }

        public void watch(int[][] board) {
            switch (type) {
                case QUAD:
                    watchOneDirection((rotation + 2) % 4, board);
                case TRIPLE:
                    watchOneDirection((rotation + 3) % 4, board);
                case ORTHOGONAL:
                    watchOneDirection((rotation + 1) % 4, board);
                case SINGLE:
                    watchOneDirection(rotation, board);
                    break;
                case DUAL:
                    watchOneDirection(rotation, board);
                    watchOneDirection((rotation + 2) % 4, board);
            }
        }

        private void watchOneDirection(int direction, int[][] board) {
            int currRow = pos.row, currCol = pos.col;

            while (board[currRow][currCol] != WALL) {
                currRow += dy[direction];
                currCol += dx[direction];
                if (currRow < 0 || currRow >= rowSize || currCol < 0 || currCol >= columnSize) break;
                if (board[currRow][currCol] == BLANK) board[currRow][currCol] = OCCUPIED;
            }
        }
    }
    
    private static int countBlank(int[][] board) {
        int result = 0;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (board[i][j] == BLANK) result++;
            }
        }

        return result;
    }

    private static int[][] getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        rowSize = Integer.parseInt(tokenizer.nextToken());
        columnSize = Integer.parseInt(tokenizer.nextToken());
        int[][] board = new int[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < columnSize; j++) {
                int elem = Integer.parseInt(tokenizer.nextToken());
                if (elem != BLANK && elem != WALL) {
                    cctvs.add(new Cctv(new Pos(i, j), elem));
                }
                board[i][j] = elem;
            }
        }
        answer = countBlank(board);

        return board;
    }

    static class Pos {
        public int row, col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
