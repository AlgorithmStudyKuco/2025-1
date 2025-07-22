import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1717 {
    static BufferedReader reader;
    static int numGroup, numOperation;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        initialize();

        for (int i = 0; i < numOperation; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            String command = tokenizer.nextToken();
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());

            if (command.equals("0")) {
                union(a, b);
            } else {
                System.out.println(find(a) == find(b) ? "YES" : "NO");
            }
        }

    }

    private static void initialize() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        numGroup = Integer.parseInt(tokenizer.nextToken()) + 1;
        numOperation = Integer.parseInt(tokenizer.nextToken());
        parents = new int[numGroup];
        for (int i = 0; i < numGroup; i++) {
            parents[i] = i;
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
}
