import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14888 {
    static int n;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }
        tokenizer = new StringTokenizer(reader.readLine());
        int[] operators = new int[4];
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(tokenizer.nextToken());
        }

        dfs(numbers[0], 1, numbers, operators);

        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int prevValue, int index, int[] numbers, int[] operators) {
        if (index == n) {
            max = Math.max(max, prevValue);
            min = Math.min(min, prevValue);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (operators[i] <= 0) {
                continue;
            }
            int currValue = 0;
            switch (i) {
                case 0:
                    currValue = prevValue + numbers[index];
                    break;
                case 1:
                    currValue = prevValue - numbers[index];
                    break;
                case 2:
                    currValue = prevValue * numbers[index];
                    break;
                case 3:
                    currValue = prevValue / numbers[index];
            }

            int[] nextOperators = operators.clone();
            nextOperators[i]--;

            dfs(currValue, index+1, numbers, nextOperators);
        }
    }
}
