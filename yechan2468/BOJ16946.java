import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ16946 {
    private static int numRow, numCol;
    private static int[][] board;
    private static boolean[][] visited;
    private static int[][] groups;
    private static int[] dr = new int[]{-1, 0, 0, 1};
    private static int[] dc = new int[]{0, -1, 1, 0};
    private static final int WALL = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        numRow = Integer.parseInt(tokenizer.nextToken());
        numCol = Integer.parseInt(tokenizer.nextToken());
        board = new int[numRow][numCol];
        visited = new boolean[numRow][numCol];
        for (int i = 0; i < numRow; i++) {
            String line = reader.readLine();
            for (int j = 0; j < numCol; j++) {
                if (line.charAt(j) == '1') {
                    board[i][j] = WALL;
                    visited[i][j] = true;
                }
            }
        }
        groups = new int[numRow][numCol];

        int groupNo = 0;
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                if (visited[i][j]) continue;
                if (board[i][j] == WALL) continue;
                HashSet<Position> history = new HashSet<>();
                dfs(i, j, history);
                for (Position position : history) {
                    board[position.row][position.col] = history.size();
                    groups[position.row][position.col] = groupNo;
                }
                groupNo++;
            }
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                if (board[i][j] == WALL) {
                    int count = 1;
                    Set<Integer> group = new HashSet<>();
                    for (int dir = 0; dir < 4; dir++) {
                        int nRow = i + dr[dir];
                        int nCol = j + dc[dir];
                        if (nRow < 0 || numRow <= nRow) continue;
                        if (nCol < 0 || numCol <= nCol) continue;
                        if (board[nRow][nCol] == WALL) continue;
                        if (group.contains(groups[nRow][nCol])) continue;
                        group.add(groups[nRow][nCol]);
                        count += board[nRow][nCol];
                        count %= 10;
                    }
                    writer.write(String.valueOf(count));
                } else {
                    writer.write("0");
                }
            }
            writer.write("\n");
        }
        writer.flush();
    }

    public static void dfs(int row, int col, HashSet<Position> history) {
        if (visited[row][col]) return;
        visited[row][col] = true;
        history.add(new Position(row, col));

        for (int dir = 0; dir < 4; dir++) {
            int nRow = row + dr[dir];
            int nCol = col + dc[dir];
            if (nRow < 0 || numRow <= nRow) continue;
            if (nCol < 0 || numCol <= nCol) continue;
            if (visited[nRow][nCol]) continue;
            dfs(nRow, nCol, history);
        }
    }

    public static class Position {
        int row, col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}