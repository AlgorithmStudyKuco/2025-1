import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(reader.readLine());
        }

        Arrays.sort(numbers);

        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                answer += numbers[n-1];
                continue;
            }

            int first = numbers[i], second = numbers[i+1];
            if (first < 0 && second < 0) {
                answer += first * second;
                i++;
            } else if (first < 0 && second == 0) {
                /* answer += 0; */
            } else if (first < 0 && second == 1) {
                answer += first + second;
                i++;
            } else if (first < 0 /* && second > 1 */) {
                if ((n - i) % 2 != 0) {
                    answer += first;
                } else {
                    answer += first + second;
                    i++;
                }
            } else if ((first == 0 || first == 1) /* && second > 1 */) {
                answer += first;
            } else if (/* first > 1 && */ second > 1) {
                if ((n - i) % 2 != 0) {
                    answer += first;
                    continue;
                }
                answer += first * second;
                i++;
            }
        }

        System.out.println(answer);
    }
}
