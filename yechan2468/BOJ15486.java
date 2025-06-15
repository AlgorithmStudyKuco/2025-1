import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15486 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        int n = Integer.parseInt(reader.readLine());
        int[] durations = new int[n];
        int[] rewards = new int[n];

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            durations[i] = Integer.parseInt(tokenizer.nextToken());
            rewards[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int[] memo = new int[n];
        if (durations[0] - 1 < n) {
            memo[durations[0] - 1] = rewards[0];
        }
        for (int i = 1; i < n; i++) {
            memo[i] = Math.max(memo[i], memo[i - 1]);
            if (n <= i + durations[i] - 1) continue;
            memo[i + durations[i] - 1] = Math.max(memo[i + durations[i] - 1], memo[i - 1] + rewards[i]);
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, memo[i]);
        }

        System.out.println(answer);
    }
}
