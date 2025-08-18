import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15684 {
    static int numColumn, numBridge, numRow, answer;
    static boolean[][] bridges;
    static final int MAX_DEPTH = 3;
    
    public static void main(String[] args) throws IOException {
        initialize();

        if (validate()) {
            System.out.println(0);
            return;
        }

        dfs(0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void dfs(int lastTriedRow, int depth) {
        if (depth > MAX_DEPTH) return;
        if (validate()) answer = Math.min(answer, depth);

        for (int i = lastTriedRow; i < numRow; i++) {
            for (int j = 0; j < numColumn - 1; j++) {
                if (!isPossibleToAddBridge(i, j)) continue;

                bridges[i][j] = true;
                dfs(i,depth + 1);
                bridges[i][j] = false;
            }
        }
    }

    private static boolean isPossibleToAddBridge(int row, int column) {
        if (bridges[row][column]) return false;
        if (column > 0 && bridges[row][column - 1]) return false;
        if (column < numColumn - 2 && bridges[row][column + 1]) return false;
        return true;
    }

    private static boolean validate() {
        for (int i = 0; i < numColumn - 1; i++) {
            if (!simulateLadderGame(i)) return false;
        }
        return true;
    }

    private static boolean simulateLadderGame(int startColumn) {
        int currentColumn = startColumn;

        int currentRow = 0;
        while (currentRow < numRow) {
            if (currentColumn > 0 && bridges[currentRow][currentColumn - 1]) {
                currentColumn--;
            }
            else if (currentColumn < numColumn - 1 && bridges[currentRow][currentColumn]) {
                currentColumn++;
            }
            currentRow++;
        }

        return currentColumn == startColumn;
    }
    
    private static void initialize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        numColumn = Integer.parseInt(tokenizer.nextToken());
        numBridge = Integer.parseInt(tokenizer.nextToken());
        numRow = Integer.parseInt(tokenizer.nextToken());
        bridges = new boolean[numRow][numColumn - 1];
        for (int i = 0; i < numBridge; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int row = Integer.parseInt(tokenizer.nextToken()) - 1;
            int column = Integer.parseInt(tokenizer.nextToken()) - 1;
            bridges[row][column] = true;
        }
        answer = Integer.MAX_VALUE;
    }
}
