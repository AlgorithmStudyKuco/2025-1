import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ9527 {
    static long a, b;
    static long[] sums;
    static Map<Long, Long> memo;

    public static void main(String[] args) throws IOException {
        initialize();

        sums[0] = 1;
        for (int i = 1; i < 60; i++) {
            sums[i] = sums[i - 1] * 2 + (1L << i);
        }

        System.out.println(get(b) - get(a - 1));
    }

    private static long get(long x) {
        if (memo.containsKey(x)) return memo.get(x);

        long highest = Long.highestOneBit(x);
        int k = log2(highest);

        long result = (k > 0 ? sums[k - 1] : 0) + (x - highest + 1) + get(x - highest);
        memo.put(x, result);

        return result;
    }

    private static int log2(long x) {
        int result = 0;
        while (x != 1) {
            x >>= 1;
            result++;
        }
        return result;
    }

    private static void initialize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        a = Long.parseLong(tokenizer.nextToken());
        b = Long.parseLong(tokenizer.nextToken());
        sums = new long[60];
        memo = new HashMap<>();
        for (long i = 0; i < 3; i++) {
            memo.put(i, i);
        }
    }
}
