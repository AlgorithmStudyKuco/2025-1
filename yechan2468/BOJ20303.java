import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ20303 {
    private static int numChild, numRelation, threshold, answer;
    private static int[] candies, parents;
    private static boolean[] visited;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        initialize();

        List<Integer> weights = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < numChild; i++) {
            if (visited[i]) continue;
            Result result = dfs(i);
            weights.add(result.personCount);
            values.add(result.candyCount);
        }
        int[][] memo = new int[values.size()][threshold];
        if (weights.get(0) < threshold) {
            memo[0][weights.get(0)] = values.get(0);
        }

        for (int i = 1; i < values.size(); i++) {
            for (int j = 0; j < threshold; j++) {
                if (weights.get(i) <= j) {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i - 1][j - weights.get(i)] + values.get(i));
                } else {
                    memo[i][j] = memo[i - 1][j];
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < values.size(); i++) {
            answer = Math.max(answer, memo[i][threshold - 1]);
        }

        System.out.println(answer);
    }

    private static Result dfs(int curr) {
        int candyCount = candies[curr];
        int personCount = 1;

        visited[curr] = true;
        for (int friend : graph.get(curr)) {
            if (find(curr) == find(friend)) continue;
            union(curr, friend);
            Result result = dfs(friend);
            personCount += result.personCount;
            candyCount += result.candyCount;
        }

        return new Result(candyCount, personCount);
    }

    private static class Result {
        int candyCount, personCount;

        public Result(int candyCount, int personCount) {
            this.candyCount = candyCount;
            this.personCount = personCount;
        }
    }

    private static int find(int x) {
        if (x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }

    private static void union(int a, int b) {
        parents[find(b)] = find(a);
    }

    private static void initialize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        numChild = Integer.parseInt(tokenizer.nextToken());
        numRelation = Integer.parseInt(tokenizer.nextToken());
        threshold = Integer.parseInt(tokenizer.nextToken());
        candies = new int[numChild];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < numChild; i++) {
            candies[i] = Integer.parseInt(tokenizer.nextToken());
        }
        graph = new ArrayList<>();
        for (int i = 0; i < numChild; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < numRelation; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken()) - 1;
            int b = Integer.parseInt(tokenizer.nextToken()) - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        parents = new int[numChild];
        for (int i = 0; i < numChild; i++) {
            parents[i] = i;
        }
        visited = new boolean[numChild];
        answer = 0;
    }
}
