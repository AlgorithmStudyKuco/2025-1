import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1562 {
    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[][] memo = new int[10][1 << 10];
        for (int i = 1; i < 10; i++) {
            memo[i][1 << i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            int[][] newMemo = new int[10][1 << 10];
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < (1 << 10); k++) {
                    if (j >= 1) {
                        newMemo[j - 1][k | (1 << (j - 1))] += memo[j][k];
                        newMemo[j - 1][k | (1 << (j - 1))] %= MOD;
                    }
                    if (j <= 8) {
                        newMemo[j + 1][k | (1 << (j + 1))] += memo[j][k];
                        newMemo[j + 1][k | (1 << (j + 1))] %= MOD;
                    }
                }

            }
            memo = newMemo;
        }

        int answer = 0;
        for (int i = 0; i < 10; i++) {
            answer += memo[i][0b1111111111];
            answer %= MOD;
        }

        System.out.println(answer);
    }
}
