import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2357 {
    static int n, k, numQuery;
    static int[] numbers;
    static int[][] queries;
    static int[][] minSparseTable, maxSparseTable;

    public static void main(String[] args) throws IOException {
        getInput();

        initializeSparseTable();

        for (int i = 0; i < numQuery; i++) {
            System.out.println(query(queries[i][0], queries[i][1]));
        }
    }

    private static void initializeSparseTable() {
        minSparseTable = new int[k][1 << k];
        maxSparseTable = new int[k][1 << k];

        for (int i = 0; i < 1 << k; i++) {
            minSparseTable[0][i] = maxSparseTable[0][i] = numbers[i];
        }
        for (int i = 1; i < k; i++) {
            for (int l = 0; l < (1 << k) - (1 << i - 1); l++) {
                int len = 1 << i;
                int mid = l + len / 2;
                minSparseTable[log2(len)][l] = Math.min(
                        minSparseTable[log2(len) - 1][l] == 0 ? Integer.MAX_VALUE : minSparseTable[log2(len) - 1][l],
                        minSparseTable[log2(len) - 1][mid] == 0 ? Integer.MAX_VALUE : minSparseTable[log2(len) - 1][mid]
                );
                maxSparseTable[log2(len)][l] = Math.max(
                        maxSparseTable[log2(len) - 1][l],
                        maxSparseTable[log2(len) - 1][mid]
                );
            }
        }
    }

    private static Answer query(int a, int b) {
        int p = Integer.highestOneBit(b - a + 1);
        int min = Math.min(minSparseTable[log2(p)][a], minSparseTable[log2(p)][b - p + 1]);
        int max = Math.max(maxSparseTable[log2(p)][a], maxSparseTable[log2(p)][b - p + 1]);
        return new Answer(min, max);
    }

    private static class Answer {
        int min, max;

        public Answer(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return min + " " + max;
        }
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        numQuery = Integer.parseInt(tokenizer.nextToken());
        k = log2(100_000) + 1;
        numbers = new int[1 << k];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(reader.readLine());
        }
        queries = new int[numQuery][2];
        for (int i = 0; i < numQuery; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            queries[i][0] = Integer.parseInt(tokenizer.nextToken()) - 1;
            queries[i][1] = Integer.parseInt(tokenizer.nextToken()) - 1;
        }
    }

    private static int log2(int x) {
        int result = 0;
        while ((x >>= 1) != 0) {
            result++;
        }

        return result;
    }
}
