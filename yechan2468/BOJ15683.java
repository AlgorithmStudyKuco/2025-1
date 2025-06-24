import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15683 {
    static int rowSize, columnSize;
    static int[][] board;
    static ArrayList<Pos> blanks, walls;
    static ArrayList<Cctv> cctvs;
    static final int BLANK = 0, SINGLE = 1, DUAL = 2, ORTHOGONAL = 3, TRIPLE = 4, QUAD = 5, WALL = 6;

    public static void main(String[] args) throws IOException {
        getInput();

        boolean[] visited = new boolean[cctvs.size()];

    }

    private static void combination(int depth, )

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        rowSize = Integer.parseInt(tokenizer.nextToken());
        columnSize = Integer.parseInt(tokenizer.nextToken());
        board = new int[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < columnSize; j++) {
                int elem = Integer.parseInt(tokenizer.nextToken());
                Pos pos = new Pos(i, j);
                switch (elem) {
                    case BLANK:
                        blanks.add(pos);
                        break;
                    case WALL:
                        walls.add(pos);
                        break;
                    default:
                        cctvs.add(new Cctv(pos, elem));
                }
                board[i][j] = elem;
            }
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
    }

    static class SingleCctv extends Cctv {
        public SingleCctv(Pos pos, int type) {
            super(pos, type);
        }

        public void watch() {
            pos.row
        }
    }

    static class Pos {
        public int row, col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
