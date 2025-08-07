import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2437 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Integer[] weights = new Integer[n];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(weights);

        int sum = 0;
        for (int w : weights) {
            if (w > sum + 1) break;
            sum += w;
        }
        System.out.println(sum + 1);
    }
}
