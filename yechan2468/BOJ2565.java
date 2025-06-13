import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2565 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[][] lines = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            lines[i][0] = Integer.parseInt(tokenizer.nextToken());
            lines[i][1] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(lines, Comparator.comparingInt(x -> x[0]));
        int[] memo = new int[501];

        int answer = 0;
        for (int[] line : lines) {
            int end = line[1];
            int max = 0;
            for (int i = 0; i <= end; i++) {
                max = Math.max(max, memo[i]);
            }
            memo[end] = max + 1;
            answer = Math.max(answer, memo[end]);
        }

        System.out.println(n - answer);
    }
}
