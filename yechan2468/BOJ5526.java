import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ5526 {
    private static int numArea, limit;
    private static long[] points;

    public static void main(String[] args) throws IOException {
        getInput();

        List<Long> sumOfTwoDarts = getSumOfTwoDarts();

        long answer = 0;
        for (long i : sumOfTwoDarts) {
            int index = Collections.binarySearch(sumOfTwoDarts, limit - i);

            if (index < 0) {
                index = -index - 2;
            }

            if (0 <= index && index < sumOfTwoDarts.size()) {
                answer = Math.max(answer, i + sumOfTwoDarts.get(index));
            }
        }

        System.out.println(answer);
    }

    private static List<Long> getSumOfTwoDarts() {
        List<Long> result = new ArrayList<>();
        result.add(0L);
        for (int i = 0; i < numArea; i++) {
            result.add(points[i]);
        }
        for (int i = 0; i <= points.length; i++) {
            for (int j = 0; j <= points.length; j++) {
                long sum = result.get(i) + result.get(j);
                if (sum <= limit) {
                    result.add(sum);
                }
            }
        }

        HashSet<Long> resultSet = new HashSet<>(result);
        result = new ArrayList<>(resultSet);
        Collections.sort(result);

        return result;
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        numArea = Integer.parseInt(tokenizer.nextToken());
        limit = Integer.parseInt(tokenizer.nextToken());
        points = new long[numArea];
        for (int i = 0; i < numArea; i++) {
            points[i] = Integer.parseInt(reader.readLine());
        }
    }
}
