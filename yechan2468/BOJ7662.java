import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ7662 {
    static int n;
    static Queue<Node> minQueue, maxQueue;
    static Node[] instructions;
    static final String INSERT = "I";
    static final int DEL_MAX = 1, DEL_MIN = -1;
    static boolean[] isDeleted;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());
        for (int i = 0; i < t; i++) {
            initialize(reader);
            solve();
        }
    }

    private static void solve() {
        for (Node instruction : instructions) {
            if (instruction.command.equals(INSERT)) {
                minQueue.offer(instruction);
                maxQueue.offer(instruction);
                continue;
            }

            if (instruction.number == DEL_MAX) {
                poll(maxQueue);
            } else {
                poll(minQueue);
            }
        }

        Optional<Integer> max = poll(maxQueue, true);
        if (max.isEmpty()) {
            System.out.println("EMPTY");
            return;
        }

        System.out.print(max.get() + " ");
        Optional<Integer> max1 = poll(maxQueue, true);
        if (max1.isEmpty()) {
            System.out.println(max.get());
            return;
        }

        Optional<Integer> min = poll(minQueue);
        if (min.isEmpty()) return;
        System.out.println(min.get());
    }

    private static Optional<Integer> poll(Queue<Node> queue) {
        return poll(queue, false);
    }

    private static Optional<Integer> poll(Queue<Node> queue, boolean ignoreDeletion) {
        while (true) {
            Node node = queue.poll();
            if (node == null) return Optional.empty();
            if (isDeleted[node.index]) continue;
            if (!ignoreDeletion) isDeleted[node.index] = true;
            return Optional.of(node.number);
        }
    }

    private static void initialize(BufferedReader reader) throws IOException {
        n = Integer.parseInt(reader.readLine());
        minQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.number));
        maxQueue = new PriorityQueue<>((a, b) -> Integer.compare(b.number, a.number));
        instructions = new Node[n];
        for (int j = 0; j < n; j++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            String command = tokenizer.nextToken();
            int number = Integer.parseInt(tokenizer.nextToken());
            instructions[j] = new Node(j, command, number);
        }
        isDeleted = new boolean[n];
    }

    private static class Node {
        int index, number;
        String command;

        public Node(int index, String command, int number) {
            this.index = index;
            this.number = number;
            this.command = command;
        }
    }
}
