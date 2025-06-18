import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ5014 {
    static int maxFloor, startFloor, targetFloor, up, down;
    static int[] counts;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        maxFloor = Integer.parseInt(tokenizer.nextToken());
        startFloor = Integer.parseInt(tokenizer.nextToken());
        targetFloor = Integer.parseInt(tokenizer.nextToken());
        up = Integer.parseInt(tokenizer.nextToken());
        down = Integer.parseInt(tokenizer.nextToken());

        counts = new int[maxFloor + 1];
        Arrays.fill(counts, Integer.MAX_VALUE);

        solve();

        System.out.println("use the stairs");
    }

    static void solve() {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(startFloor, 0));

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            if (counts[curr.floor] != Integer.MAX_VALUE) continue;
            counts[curr.floor] = curr.count;

            if (curr.floor == targetFloor) {
                System.out.println(curr.count);
                System.exit(0);
            }

            if (curr.floor + up <= maxFloor && counts[curr.floor + up] == Integer.MAX_VALUE) {
                queue.offer(new Pair(curr.floor + up, curr.count + 1));
            }
            if (1 <= curr.floor - down && counts[curr.floor - down] == Integer.MAX_VALUE) {
                queue.offer(new Pair(curr.floor - down, curr.count + 1));
            }
        }
    }

    static class Pair {
        int floor, count;

        public Pair(int floor, int count) {
            this.floor = floor;
            this.count = count;
        }
    }
}