import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ9466 {
    static int n;
    static Map<Integer, List<Integer>> graph;
    static boolean[] visited;
    static int answer;
    static boolean isCycleFound;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numTestcase = Integer.parseInt(reader.readLine());
        for (int t = 0; t < numTestcase; t++) {
            initialize(reader);
            solve();
        }
    }

    private static void solve() {
        answer = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            Stack<Integer> path = new Stack<>();
            isCycleFound = false;
            int newVisitedCount = dfs(i, i, path);

            answer = answer + newVisitedCount - path.size();
        }

        System.out.println(answer);
    }

    private static int dfs(int curr, int start, Stack<Integer> history) {
        visited[curr] = true;
        int visitCount = 0;
        visitCount++;

        if (!isCycleFound) history.add(curr);
        for (int next : graph.get(curr)) {
            if (next == start) isCycleFound = true;
            if (visited[next]) continue;
            visited[next] = true;
            visitCount += dfs(next, start, history);
        }
        if (!isCycleFound) history.pop();

        return visitCount;
    }

    private static void initialize(BufferedReader reader) throws IOException {
        n = Integer.parseInt(reader.readLine());
        graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            graph.get(Integer.parseInt(tokenizer.nextToken()) - 1).add(i);
        }
        visited = new boolean[n];
        answer = 0;
    }
}
