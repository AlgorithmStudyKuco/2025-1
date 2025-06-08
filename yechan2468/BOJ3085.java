import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ3085 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            String row = reader.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = row.charAt(j);
            }
        }

        int answer = 0;

        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n; j++) {
                swap(i, j, i+1, j, board);
                answer = Math.max(answer, count(i, j, n, board));
                swap(i, j, i+1, j, board);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-1; j++) {
                swap(i, j, i, j+1, board);
                answer = Math.max(answer, count(i, j, n, board));
                swap(i, j, i, j+1, board);
            }
        }

        System.out.println(answer);
    }

    private static int count(int i, int j, int n, char[][] board) {
        int result = 0;
        int cnt = 1;
        for (int k = 0; k < n-1; k++) {
            cnt = (board[i][k] == board[i][k+1]) ? cnt+1 : 1;
            result = Math.max(result, cnt);
        }
        cnt = 1;
        for (int k = 0; k < n-1; k++) {
            if (i+1 >= n) break;
            cnt = (board[i+1][k] == board[i+1][k+1]) ? cnt+1 : 1;
            result = Math.max(result, cnt);
        }
        cnt = 1;
        for (int k = 0; k < n-1; k++) {
            cnt = (board[k][j] == board[k+1][j]) ? cnt+1 : 1;
            result = Math.max(result, cnt);
        }
        cnt = 1;
        for (int k = 0; k < n-1; k++) {
            if (j+1 >= n) break;
            cnt = (board[k][j+1] == board[k+1][j+1]) ? cnt+1 : 1;
            result = Math.max(result, cnt);
        }

        return result;
    }

    static void swap(int y1, int x1, int y2, int x2, char[][] board) {
        char temp = board[y1][x1];
        board[y1][x1] = board[y2][x2];
        board[y2][x2] = temp;
    }
}
