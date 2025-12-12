import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ12015 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] numbers = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }
        int[] memo = new int[n];
        Arrays.fill(memo, Integer.MAX_VALUE);

        for (int number : numbers) {
            int index = Arrays.binarySearch(memo, number);
            if (index < 0) {
                index = -index - 1;
            }
            memo[index] = Math.min(memo[index], number);
        }

        for (int i = n - 1; i >= 0; i--) {
            if (memo[i] != Integer.MAX_VALUE) {
                System.out.println(i + 1);
                return;
            }
        }
    }
}
