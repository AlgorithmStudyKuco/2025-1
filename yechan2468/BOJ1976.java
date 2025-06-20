import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1976 {
    static final String POSSIBLE = "YES";
    static final String IMPOSSIBLE = "NO";
    static final int INF = 10_000;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(tokenizer.nextToken()) == 0 ? INF : 1;
            }
        }
        int[] plans = new int[m];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < m; i++) {
            plans[i] = Integer.parseInt(tokenizer.nextToken()) - 1;
        }

        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    graph[i][j] = graph[j][i] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);

        if (n == 1) {
            System.out.println(POSSIBLE);
            return;
        }

        for (int i = 1; i < m; i++) {
            if (graph[plans[i-1]][plans[i]] == INF) {
                System.out.println(IMPOSSIBLE);
                return;
            }
        }
        System.out.println(POSSIBLE);
    }
}
