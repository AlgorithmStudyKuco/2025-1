import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;
import java.util.StringTokenizer;

public class BOJ1253 {
    static int n;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        numbers = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(numbers);

        int answer = 0;
        LOOP: for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                Optional<Integer> candidate = binarySearch(numbers[i] - numbers[j], i, j);
                if (candidate.isPresent()) {
                    answer++;
                    continue LOOP;
                }
            }
        }

        System.out.println(answer);
    }

    static Optional<Integer> binarySearch(int target, int exclude1, int exclude2) {
        int left = 0, right = n - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (numbers[mid] == target) {
                if (mid == exclude1 || mid == exclude2) {
                    for (int i = -1; i <= 1; i++) {
                        if (0 > mid + i || mid + i >= n) continue;
                        if (numbers[mid + i] != numbers[mid]) continue;
                        if (mid + i == exclude1 || mid + i == exclude2) continue;
                        return Optional.of(mid + i);
                    }
                    return Optional.empty();
                }

                return Optional.of(mid);
            } else if (numbers[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return Optional.empty();
    }
}
