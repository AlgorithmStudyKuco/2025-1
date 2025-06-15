import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BOJ20500 {
    static int n;
    static long[] factorials;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        factorials = getFactorials();

        long answer = 0;
        for (int i = 0; i < n; i++) {
            if ((n + 4 + i * 4) % 3 == 0) {
                answer += binomial(n - 1, i);
                answer %= MOD;
            }
        }

        System.out.println(answer);
    }

    static long modInverse(long x) {
        return BigInteger.valueOf(x)
                .modPow(BigInteger.valueOf(MOD - 2), BigInteger.valueOf(MOD))
                .longValue();
    }

    static long binomial(int n, int k) {
        return factorials[n] * modInverse((factorials[k] * factorials[n - k]) % MOD) % MOD;
    }

    static long[] getFactorials() {
        long[] factorials = new long[n+1];
        factorials[0] = factorials[1] = 1;
        for (int i = 2; i <= n; i++) {
            factorials[i] = (factorials[i-1] * i) % MOD;
        }

        return factorials;
    }
}
