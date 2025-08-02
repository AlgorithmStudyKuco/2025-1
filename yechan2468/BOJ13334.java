import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ13334 {
    static int n, metroLength;
    static int[][] transitInputs;

    public static void main(String[] args) throws IOException {
        getInput();

        Map<Integer, List<Boolean>> transits = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int start = transitInputs[i][0];
            int end = transitInputs[i][1];

            if (end - start > metroLength) continue;

            transits.putIfAbsent(end - metroLength, new ArrayList<>());
            transits.get(end - metroLength).add(Boolean.TRUE);
            transits.putIfAbsent(start + 1, new ArrayList<>());
            transits.get(start + 1).add(Boolean.FALSE);
        }

        List<Integer> keys = new ArrayList<>(transits.keySet());
        Collections.sort(keys);

        int count = 0;
        int answer = 0;
        for (int index : keys) {
            for (boolean b : transits.get(index)) {
                count += (b ? 1 : -1);
            }
            answer = Math.max(answer, count);
        }

        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(reader.readLine());

        transitInputs = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            transitInputs[i][0] = Math.min(a, b);
            transitInputs[i][1] = Math.max(a, b);
        }

        metroLength = Integer.parseInt(reader.readLine());
    }
}
