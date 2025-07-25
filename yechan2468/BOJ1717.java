import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1717 {
    static int[] parents;
    static final String UNION = "0";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = initialize(reader);

        for (int i = 0; i < n; i++) {
            Operation op = getOperation(reader);
            String command = op.command; int a = op.a, b = op.b;

            if (command.equals(UNION)) {
                union(a, b);
            } else {
                System.out.println(find(a) == find(b) ? "YES" : "NO");
            }
        }
    }

    private static void union(int a, int b) {
        a = find(a); b = find(b);
        if (a == b) return;
        parents[a] = b;
    }

    private static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    private static int initialize(BufferedReader reader) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int numGroup = Integer.parseInt(tokenizer.nextToken()) + 1;
        int numOperation = Integer.parseInt(tokenizer.nextToken());
        parents = new int[numGroup];
        for (int i = 0; i < numGroup; i++) {
            parents[i] = i;
        }

        return numOperation;
    }

    private static Operation getOperation(BufferedReader reader) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        String command = tokenizer.nextToken();
        int a = Integer.parseInt(tokenizer.nextToken());
        int b = Integer.parseInt(tokenizer.nextToken());

        return new Operation(command, a, b);
    }

    private static class Operation {
        public final String command;
        public final int a, b;

        public Operation(String command, int a, int b) {
            this.command = command;
            this.a = a;
            this.b = b;
        }
    }
}
