import java.io.*;
import java.util.StringTokenizer;

public class BOJ2042_1 {
    static final String UPDATE = "1";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(tokenizer.nextToken());
        long[] numbers = new long[n];
        FenwickTree tree = new FenwickTree(n);
        for (int i = 0; i < n; i++) {
            numbers[i] = Long.parseLong(reader.readLine());
            tree.update(i, numbers[i]);
        }

        int numUpdate = Integer.parseInt(tokenizer.nextToken());
        int numQuery = Integer.parseInt(tokenizer.nextToken());

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < numUpdate + numQuery; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String operation = tokenizer.nextToken();

            if (operation.equals(UPDATE)) {
                int targetIndex = Integer.parseInt(tokenizer.nextToken()) - 1;
                long newNumber = Long.parseLong(tokenizer.nextToken());
                tree.update(targetIndex, newNumber - numbers[targetIndex]);
                numbers[targetIndex] = newNumber;
            } else {
                int startIndex = Integer.parseInt(tokenizer.nextToken()) - 1;
                int endIndex = Integer.parseInt(tokenizer.nextToken()) - 1;
                long answer = tree.query(endIndex) - tree.query(startIndex) + numbers[startIndex];

                writer.write(answer + "\n");
            }
        }

        writer.flush();
    }

    static private class FenwickTree {
        private final long[] tree;
        private final int size;

        public FenwickTree(int n) {
            tree = new long[n + 1];
            size = n;
        }

        public void update(int index, long delta) {
            index++;
            while (index <= size) {
                tree[index] += delta;
                index += (index & -index);
            }
        }

        public long query(int index) {
            index++;
            long answer = 0;
            while (index > 0) {
                answer += tree[index];
                index -= (index & -index);
            }

            return answer;
        }
    }

}
