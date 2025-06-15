import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ20500 {
    static long[] factorials;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        factorials = new long[n+1];

        long answer = 0;
        for (int i = 0; i < n; i++) {
            if ((n + 4 + i * 4) % 3 == 0) {
                answer += binomial(n - 1, i);
                answer %= MOD;
            }
        }

        System.out.println(answer);
    }

    static long modPow(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return result;
    }

    static long modInverse(long x) {
        return modPow(x, MOD - 2);
    }

    static long binomial(int n, int k) {
        return (getFactorial(n) * modInverse((getFactorial(k) * getFactorial(n - k)) % MOD) % MOD);
    }

    static long getFactorial(int number) {
        if (number == 0 || number == 1) return 1;
        return factorials[number] = (getFactorial(number - 1) * number) % MOD;
    }
}
