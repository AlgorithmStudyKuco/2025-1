import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ5526 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int numArea = Integer.parseInt(tokenizer.nextToken());
        int limit = Integer.parseInt(tokenizer.nextToken());
        long[] points = new long[numArea];
        for (int i = 0; i < numArea; i++) {
            points[i] = Integer.parseInt(reader.readLine());
        }

        List<Long> combinations = new ArrayList<>();
        combinations.add(0L);
        for (int i = 0; i < numArea; i++) {
            combinations.add(points[i]);
        }
        int length = combinations.size();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                combinations.add(combinations.get(i) + combinations.get(j));
            }
        }

        Set<Long> combinationSet = new HashSet<>(combinations);
        combinations = new ArrayList<>(combinationSet);
        Collections.sort(combinations);

        long answer = 0;
        for (long i : combinations) {
            long target = limit - i;
            int index = Collections.binarySearch(combinations, target);

            if (index < 0) {
                index = -index - 2;
            }

            if (0 <= index && index < combinations.size()) {
                long targetValue = combinations.get(index);
                answer = Math.max(answer, i + targetValue);
            }
        }

        System.out.println(answer);
    }
}
