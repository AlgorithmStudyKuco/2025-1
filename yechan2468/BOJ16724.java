import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

public class BOJ16724 {
    private static int numRow, numCol;
    private static char[][] board;
    private static boolean[][] visited;
    private static Point[][] parents;
    private static final char LEFT = 'L', RIGHT = 'R', UP = 'U', DOWN = 'D';

    public static void main(String[] args) throws IOException {
        initialize();

        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                if (visited[i][j]) continue;
                unionWithItsParent(i, j);
            }
        }

        int answer = getRepresentativeParentCount();

        System.out.println(answer);
    }

    private static void unionWithItsParent(int i, int j) {
        switch (board[i][j]) {
            case LEFT:
                if (j == 0) return;
                union(i, j, i, j -1);
                break;
            case RIGHT:
                if (j == numCol - 1) return;
                union(i, j, i, j +1);
                break;
            case UP:
                if (i == 0) return;
                union(i, j, i -1, j);
                break;
            case DOWN:
                if (i == numRow - 1) return;
                union(i, j, i +1, j);
        }
    }

    private static int getRepresentativeParentCount() {
        HashSet<Point> points = new HashSet<>();
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                points.add(parents[i][j]);
            }
        }
        return points.size();
    }

    private static void union(int r1, int c1, int r2, int c2) {
        Point a = find(r1, c1);
        Point b = find(r2, c2);
        if (a.equals(b)) return;
        b.row = a.row;
        b.col = a.col;
    }

    private static Point find(int r, int c) {
        if (visited[r][c]) return parents[r][c];
        visited[r][c] = true;

        switch (board[r][c]) {
            case LEFT:
                if (c == 0) return parents[r][c];
                return parents[r][c] = find(r, c-1);
            case RIGHT:
                if (c == numCol - 1) return parents[r][c];
                return parents[r][c] = find(r, c+1);
            case UP:
                if (r == 0) return parents[r][c];
                return parents[r][c] = find(r-1, c);
            case DOWN:
                if (r == numRow - 1) return parents[r][c];
                return parents[r][c] = find(r+1, c);
        }

        return parents[r][c];
    }

    private static void initialize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        numRow = Integer.parseInt(tokenizer.nextToken());
        numCol = Integer.parseInt(tokenizer.nextToken());
        board = new char[numRow][numCol];
        for (int i = 0; i < numRow; i++) {
            String line = reader.readLine();
            for (int j = 0; j < numCol; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        parents = new Point[numRow][numCol];
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                parents[i][j] = new Point(i, j);
            }
        }
        visited = new boolean[numRow][numCol];
    }

    private static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return row == point.row && col == point.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}
