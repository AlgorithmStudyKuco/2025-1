import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ2042_0 {
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
        Map<Integer, Long> updates = new HashMap<>();
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
                for (int updateIndex: updates.keySet()) {
                    if (startIndex <= updateIndex && updateIndex <= endIndex) {
                        answer -= numbers[updateIndex];
                        answer += updates.get(updateIndex);
                    }
                }
                writer.write(answer + "\n");
            }
        }

        writer.flush();
    }
}
