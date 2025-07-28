import java.io.*;
import java.util.StringTokenizer;

public class BOJ2042_2 {
    static int n;
    static final String UPDATE = "1";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(tokenizer.nextToken());
        long[] numbers = new long[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Long.parseLong(reader.readLine());
        }
        SegmentTree tree = new SegmentTree(numbers);

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
                long answer = tree.query(startIndex, endIndex);

                writer.write(answer + "\n");
            }
        }

        writer.flush();
    }

    static private class SegmentTree {
        private final long[] tree;
        private int n = 1;

        public SegmentTree(long[] input) {
            while (n < input.length) {
                n <<= 1;
            }
            tree = new long[2 * n];
            long[] arr = new long[n];
            System.arraycopy(input, 0, arr, 0, input.length);
            build(1, 0, arr.length - 1, arr);
        }

        private void build(int curr, int l, int r, long[] arr) {
            if (l == r) {
                tree[curr] = arr[l];
                return;
            }

            int mid = (l + r) / 2;
            build(curr * 2, l, mid, arr);
            build(curr * 2 + 1, mid + 1, r, arr);
            tree[curr] = tree[curr * 2] + tree[curr * 2 + 1];
        }

        public void update(int index, long delta) {
            index += n;
            tree[index] += delta;
            for (index /= 2; index >= 1; index /= 2) {
                tree[index] = tree[2 * index] + tree[2 * index + 1];
            }
        }

        public long query(int start, int end) {
            start += n; end += n;
            long result = 0L;

            while (start <= end) {
                if (start % 2 == 1) result += tree[start++];
                if (end % 2 == 0) result += tree[end--];
                start /= 2; end /= 2;
            }

            return result;
        }
    }

}
