import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2473 {

    private static int n;
    private static int[] phs;
    private static long sum;
    private static int[] answer;

    public static void main(String[] args) throws IOException {
        initialize();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                int base = phs[i] + phs[j];
                int index = Arrays.binarySearch(phs, -base);
                if (index < 0) {
                    int insertionPoint = -index - 1;
                    check(insertionPoint - 2, i, j);
                    check(insertionPoint - 1, i, j);
                    check(insertionPoint, i, j);
                    check(insertionPoint + 1, i, j);
                    check(insertionPoint + 2, i, j);
                } else {
                    check(index, i, j);
                }
            }
        }

        Arrays.sort(answer);
        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
    }

    private static void check(int index, int i, int j) {
        if (index < 0 || index >= n) return;
        if (index == i || index == j) return;

        int base = phs[i] + phs[j];
        long abs = Math.abs((long) base + phs[index]);
        if (abs < sum) {
            sum = abs;
            answer = new int[]{phs[i], phs[j], phs[index]};
        }
    }

    private static void initialize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        phs = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            phs[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(phs);
        sum = Long.MAX_VALUE;
        answer = new int[3];
    }
}
