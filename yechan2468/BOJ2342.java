import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2342 {
    private static int[] instructions;
    private static int[][][] memo;

    public static void main(String[] args) throws IOException {
        initialize();

        for (int i = 0; i < instructions.length - 1; i++) {
            int next = instructions[i + 1];

            for (int l = 0; l < 5; l++) {
                for (int r = 0; r < 5; r++) {
                    if (memo[i][l][r] == Integer.MAX_VALUE) continue;

                    int leftForce = getNextForce(l, next);
                    memo[i + 1][next][r] = Math.min(memo[i + 1][next][r], memo[i][l][r] + leftForce);

                    int rightForce = getNextForce(r, next);
                    memo[i + 1][l][next] = Math.min(memo[i + 1][l][next], memo[i][l][r] + rightForce);
                }
            }
        }

        printAnswer();
    }

    private static int getNextForce(int curr, int next) {
        int result;
        if (curr == 0) {
            result = 2;
        } else if (curr == next) {
            result = 1;
        } else if (Math.abs(next - curr) % 2 == 0) {
            result = 4;
        } else {
            result = 3;
        }

        return result;
    }

    private static void printAnswer() {
        int answer = Integer.MAX_VALUE;
        for (int l = 0; l < 5; l++) {
            for (int r = 0; r < 5; r++) {
                answer = Math.min(answer, memo[instructions.length - 1][l][r]);
            }
        }

        System.out.println(answer);
    }

    private static void initialize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = tokenizer.countTokens() - 1;
        instructions = new int[n];
        for (int i = 0; i < n; i++) {
            instructions[i] = Integer.parseInt(tokenizer.nextToken());
        }
        memo = new int[n][5][5];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(memo[i][j], Integer.MAX_VALUE);
            }
        }
        memo[0][0][instructions[0]] = 2;
        memo[0][instructions[0]][0] = 2;
    }
}
