import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1106 {
    static int target, numItems;
    static int[][] items;
    static int[] memo;

    public static void main(String[] args) throws IOException {
        initialize();

        solve();

        int answer;
        for (answer = 0; answer < memo.length; answer++) {
            if (memo[answer] >= target) break;
        }
        System.out.println(answer);
    }

    private static void solve() {
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < numItems; j++) {
                int cost = items[j][0], value = items[j][1];
                if (memo[i] == -1 || i + cost >= memo.length) continue;
                memo[i + cost] = Math.max(memo[i + cost], memo[i] + value);
            }
        }
    }

    private static void initialize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        target = Integer.parseInt(tokenizer.nextToken());
        numItems = Integer.parseInt(tokenizer.nextToken());

        items = new int[numItems][2];  // (cost, value)[]
        for (int i = 0; i < numItems; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            items[i][0] = Integer.parseInt(tokenizer.nextToken());
            items[i][1] = Integer.parseInt(tokenizer.nextToken());
        }

        memo = new int[100001];
        Arrays.fill(memo, -1);
        memo[0] = 0;
    }
}
