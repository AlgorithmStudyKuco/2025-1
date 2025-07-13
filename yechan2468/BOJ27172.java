import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ27172 {
    static int n;
    static int[] numbers, scores;
    static boolean[] isNumberExist;
    static final int MAX_NUMBER = 1_000_000;

    public static void main(String[] args) throws IOException {
        initialize();

        solve();

        printAnswer();
    }

    private static void solve() {
        for (int i = 0; i < n; i++) {
            int number = numbers[i];
            int cnt = number;
            while ((cnt += number) <= MAX_NUMBER) {
                if (isNumberExist[cnt]) {
                    scores[number]++;
                    scores[cnt]--;
                }
            }
        }
    }

    private static void printAnswer() {
        for (int i = 0; i < n; i++) {
            System.out.print(scores[numbers[i]] + " ");
        }
        System.out.println();
    }

    private static void initialize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        numbers = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }
        scores = new int[MAX_NUMBER + 1];
        isNumberExist = new boolean[MAX_NUMBER + 1];
        for (int i = 0; i < n; i++) {
            isNumberExist[numbers[i]] = true;
        }
    }
}
