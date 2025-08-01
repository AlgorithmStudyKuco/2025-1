import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.IntBinaryOperator;

public class BOJ2357 {
    static int numQuery;
    static int[] numbers;
    static int[][] queries;
    static SparseTable minTable, maxTable;

    public static void main(String[] args) throws IOException {
        getInput();

        minTable = new SparseTable(numbers, Math::min);
        maxTable = new SparseTable(numbers, Math::max);

        for (int i = 0; i < numQuery; i++) {
            int a = queries[i][0], b = queries[i][1];
            System.out.println(minTable.query(a, b) + " " + maxTable.query(a, b));
        }
    }

    static class SparseTable {
        private final int[][] table;
        private final IntBinaryOperator f;

        public SparseTable(int[] arr, IntBinaryOperator f) {
            this.f = f;
            int n = arr.length;
            int k = log2(n) + 1;
            table = new int[k][];
            table[0] = new int[n];
            System.arraycopy(arr, 0, table[0], 0, n);

            for (int i = 1; i < k; i++) {
                int len = 1 << i;
                table[i] = new int[n - len + 1];
                for (int left = 0; left <= n - len; left++) {
                    int mid = left + len / 2;
                    table[i][left] = f.applyAsInt(table[i - 1][left], table[i - 1][mid]);
                }
            }
        }

        public int query(int a, int b) {
            int p = Integer.highestOneBit(b - a + 1);

            return f.applyAsInt(table[log2(p)][a], table[log2(p)][b - p + 1]);
        }
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        numQuery = Integer.parseInt(tokenizer.nextToken());
        numbers = new int[n];
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