import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ1946 {
    static BufferedReader reader;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        int numTestcase = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numTestcase; i++) {
            solve();
        }
    }

    static void solve() throws IOException {
        int n = Integer.parseInt(reader.readLine());
        int[][] ranks = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            ranks[i][0] = Integer.parseInt(tokenizer.nextToken());
            ranks[i][1] = Integer.parseInt(tokenizer.nextToken());
        }

        int answer = 0;
        Arrays.sort(ranks, Comparator.comparingInt(x -> x[0]));
        int min = Integer.MAX_VALUE;
        for (int[] rank : ranks) {
            if (rank[1] < min) {
                answer++;
                min = rank[1];
            }
        }

        System.out.println(answer);
    }
}
