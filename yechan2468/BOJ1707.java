import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class BOJ1707 {
    static BufferedReader reader;
    static int vertexCount, edgeCount;
    static final int NOT_VISITED = 0;
    static final int GROUP_A = 1;
    static final int GROUP_B = 2;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        int numTestcase = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numTestcase; i++) {
            System.out.println(solve() ? "YES" : "NO");
        }
    }

    static boolean solve() throws IOException {
        Hashtable<Integer, ArrayList<Integer>> graph = getInput();

        int[] visited = new int[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            if (!dfs(i, visited[i] == NOT_VISITED ? GROUP_A : visited[i], graph, visited)) return false;
        }
        return true;
    }

    private static Hashtable<Integer, ArrayList<Integer>> getInput() throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        vertexCount = Integer.parseInt(tokenizer.nextToken());
        edgeCount = Integer.parseInt(tokenizer.nextToken());

        Hashtable<Integer, ArrayList<Integer>> graph = new Hashtable<>();
        for (int i = 0; i < vertexCount; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < edgeCount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken()) - 1;
            int end = Integer.parseInt(tokenizer.nextToken()) - 1;
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        return graph;
    }

    static boolean dfs(int currNode, int currGroup, Hashtable<Integer, ArrayList<Integer>> graph, int[] visited) {
        visited[currNode] = currGroup;
        int anotherGroup = getAnotherGroup(currGroup);

        for (int adjacent : graph.get(currNode)) {
            if (visited[adjacent] == currGroup) return false;
            if (visited[adjacent] == anotherGroup) continue;
            visited[adjacent] = anotherGroup;
            if (!dfs(adjacent, anotherGroup, graph, visited)) return false;
        }

        return true;
    }

    static int getAnotherGroup(int group) {
        if (group == NOT_VISITED) return NOT_VISITED;
        return group % 2 + 1;
    }
}
