import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        long[] numbers = new long[n];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Long.parseLong(tokenizer.nextToken());
        }

        Arrays.sort(numbers);

        int pointer0 = 0, pointer1 = n-1;
        long answer0 = 0, answer1 = 0;
        long min = Long.MAX_VALUE;
        while (pointer0 < pointer1) {
            long curr = numbers[pointer0] + numbers[pointer1];
            if (Math.abs(curr) < min) {
                min = Math.abs(curr);
                answer0 = numbers[pointer0];
                answer1 = numbers[pointer1];
            }

            if (curr < 0) {
                pointer0++;
            } else {
                pointer1--;
            }
        }

        System.out.println(answer0 + " " + answer1);
    }
}
