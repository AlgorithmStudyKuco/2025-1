import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ2042 {
    static final String UPDATE = "1";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(tokenizer.nextToken());
        long[] numbers = new long[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Long.parseLong(reader.readLine());
        }
        long[] sums = new long[n];
        sums[0] = numbers[0];
        for (int i = 1; i < n; i++) {
            sums[i] = sums[i-1] + numbers[i];
        }

        int numUpdate = Integer.parseInt(tokenizer.nextToken());
        TreeMap<Integer, Long> updates = new TreeMap<>();
        int numQuery = Integer.parseInt(tokenizer.nextToken());

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < numUpdate + numQuery; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String operation = tokenizer.nextToken();

            if (operation.equals(UPDATE)) {
                int targetIndex = Integer.parseInt(tokenizer.nextToken()) - 1;
                long newNumber = Long.parseLong(tokenizer.nextToken());
                updates.put(targetIndex, newNumber);
            } else {
                int startIndex = Integer.parseInt(tokenizer.nextToken()) - 1;
                int endIndex = Integer.parseInt(tokenizer.nextToken()) - 1;
                long answer = sums[endIndex] - sums[startIndex] + numbers[startIndex];

                Set<Map.Entry<Integer, Long>> entries = updates.subMap(startIndex, true, endIndex, true).entrySet();
                for (Map.Entry<Integer, Long> entry : entries) {
                    Integer updateIndex = entry.getKey();
                    if (startIndex <= updateIndex && updateIndex <= endIndex) {
                        answer -= numbers[updateIndex];
                        answer += entry.getValue();
                    }
                }

                writer.write(answer + "\n");
            }
        }

        writer.flush();
    }
}
