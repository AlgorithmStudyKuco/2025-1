import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ13334 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numLines = Integer.parseInt(reader.readLine());
        int[][] lineInput = new int[numLines][2];
        for (int i = 0; i < numLines; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            lineInput[i][0] = Math.min(a, b);
            lineInput[i][1] = Math.max(a, b);
        }

        int length = Integer.parseInt(reader.readLine());
        Map<Integer, List<Boolean>> lines = new HashMap<>();
        for (int i = 0; i < numLines; i++) {
            int lineStart = lineInput[i][0];
            int lineEnd = lineInput[i][1];
            if (lineEnd - lineStart > length) continue;
            lines.putIfAbsent(lineEnd - length, new ArrayList<>());
            lines.get(lineEnd - length).add(Boolean.TRUE);
            lines.putIfAbsent(lineStart + 1, new ArrayList<>());
            lines.get(lineStart + 1).add(Boolean.FALSE);
        }

        int count = 0;
        int answer = 0;
        Set<Integer> keySet = lines.keySet();
        List<Integer> keyList = new ArrayList<>(keySet);
        Collections.sort(keyList);
        for (int index : keyList) {
            for (boolean b : lines.get(index)) {
                count += (b ? 1 : -1);
            }
            answer = Math.max(answer, count);
        }

        System.out.println(answer);
    }
}