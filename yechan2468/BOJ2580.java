import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2580 {
    static final int BOARD_SIZE = 9;
    static int[][] board;
    static int[] tempArray = new int[9];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        ArrayList<BlankElement> blanks = getBlanks();

        int[] lastTried = new int[blanks.size()];
        Arrays.fill(lastTried, 1);

        LOOP: for (int i = 0; i < blanks.size(); i++) {
            BlankElement blank = blanks.get(i);

            for (int j = lastTried[i]; j <= 9; j++) {
                board[blank.row][blank.column] = j;
                if (!validateRow(blank.row)
                    || !validateColumn(blank.column)
                    || !validateSquare(blank.row, blank.column)
                ) {
                    board[blank.row][blank.column] = 0;
                    continue;
                }

                lastTried[i] = j;
                if (i == blanks.size()) break LOOP;
                else continue LOOP;
            }

            board[blank.row][blank.column] = 0;
            lastTried[i] = 1;
            lastTried[i-1]++;
            i -= 2;
        }

        printAnswer();
    }

    private static ArrayList<BlankElement> getBlanks() {
        ArrayList<BlankElement> blanks = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == 0) {
                    blanks.add(new BlankElement(i, j));
                }
            }
        }

        return blanks;
    }

    static boolean validateRow(int row) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            tempArray[i] = board[row][i];
        }
        return !isDuplicated();
    }

    static boolean validateColumn(int column) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            tempArray[i] = board[i][column];
        }
        return !isDuplicated();
    }

    static boolean validateSquare(int row, int column) {
        for (int i = 0; i < 9; i++) {
            tempArray[i] = board[(row / 3 * 3) + (i / 3)][(column / 3 * 3) + (i % 3)];
        }
        return !isDuplicated();
    }

    static boolean isDuplicated() {
        Arrays.sort(tempArray);
        for (int i = 1; i < 9; i++) {
            if (tempArray[i-1] == 0) continue;
            if (tempArray[i-1] == tempArray[i]) {
                return true;
            }
        }
        return false;
    }

    private static void printAnswer() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    static class BlankElement {
        int row, column;

        public BlankElement(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
