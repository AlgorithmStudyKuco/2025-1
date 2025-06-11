import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2512 {
    static int n;
    static int[] numbers;
    static long maxBudget;

    public static void main(String[] args) throws IOException {
        getInput();

        Arrays.sort(numbers);

        int sum = 0;
        long minDiff = Long.MAX_VALUE;
        for (int i = 1; i < n+1; i++) {
            int unit = n - (i-1);
            sum += (numbers[i] - numbers[i-1]) * unit;
            long newDiff = maxBudget - sum;

            if (newDiff == 0) {
                System.out.println(numbers[i]);
                return;
            }

            if (newDiff > 0 && newDiff <= minDiff) {
                minDiff = newDiff;
            } else {
                int prevSum = sum - (numbers[i] - numbers[i-1]) * unit;
                long answer = numbers[i-1] + (maxBudget - prevSum) / unit;
                System.out.println(answer);
                return;
            }
        }
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        numbers = new int[n+1];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int numbersSum = 0;
        int maxNumber = 0;
        for (int i = 1; i < n+1; i++) {
            int number = Integer.parseInt(tokenizer.nextToken());
            numbersSum += number;
            maxNumber = Math.max(maxNumber, number);
            numbers[i] = number;
        }
        maxBudget = Long.parseLong(reader.readLine());

        if (numbersSum <= maxBudget) {
            System.out.println(maxNumber);
            System.exit(0);
        }
    }
}
