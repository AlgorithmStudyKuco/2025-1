import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ12850 {
    private static final int MOD = 1_000_000_007;
    private static final long[][] ADJ = new long[][] {
            {0, 1, 1, 0, 0, 0, 0, 0},
            {1, 0, 1, 1, 0, 0, 0, 0},
            {1, 1, 0, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 1, 0, 0},
            {0, 0, 1, 1, 0, 1, 1, 0},
            {0, 0, 0, 1, 1, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 1, 0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        long[][] powered = power(ADJ, n);
        System.out.println(powered[0][0]);
    }

    private static long[][] power(long[][] base, int exp) {
        long[][] result = new long[8][8];
        for (int i = 0; i < 8; i++) {
            result[i][i] = 1;
        }

        while (exp > 0) {
            if (exp % 2 == 1) {
                result = multiply(result, base);
            }
            base = multiply(base, base);
            exp /= 2;
        }

        return result;
    }

    private static long[][] multiply(long[][] mat1, long[][] mat2) {
        long[][] result = new long[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    result[i][j] += mat1[i][k] * mat2[k][j];
                    result[i][j] %= MOD;
                }
            }
        }

        return result;
    }
}
