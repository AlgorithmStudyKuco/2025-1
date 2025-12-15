import java.io.*;
import java.util.StringTokenizer;

public class BOJ16566 {

    static int[] nextGreaterNumbers;
    static boolean[] isAvailable;
    static int[] queries;

    public static void main(String[] args) throws IOException {
        initialize();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int query : queries) {
            int myCard = find(query);
            union(myCard - 1, myCard);
            isAvailable[myCard] = false;
            writer.write(myCard + "\n");
        }

        writer.flush();
    }

    private static void union(int a, int b) {
        nextGreaterNumbers[a] = b;
    }

    private static int find(int a) {
        if (isAvailable[nextGreaterNumbers[a]]) return nextGreaterNumbers[a];
        return nextGreaterNumbers[a] = find(nextGreaterNumbers[a]);
    }

    private static void initialize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int maxNumber = Integer.parseInt(tokenizer.nextToken());
        int myCardCount = Integer.parseInt(tokenizer.nextToken());
        int queryCount = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(reader.readLine());
        int[] myCards = new int[myCardCount];
        for (int i = 0; i < myCardCount; i++) {
            myCards[i] = (Integer.parseInt(tokenizer.nextToken()));
        }
        tokenizer = new StringTokenizer(reader.readLine());
        queries = new int[queryCount];
        for (int i = 0; i < queryCount; i++) {
            queries[i] = Integer.parseInt(tokenizer.nextToken());
        }
        isAvailable = new boolean[maxNumber + 1];
        for (int i = 0; i < myCardCount; i++) {
            isAvailable[myCards[i]] = true;
        }
        nextGreaterNumbers = new int[maxNumber + 1];
        int nextGreaterNumber = 0;
        for (int i = maxNumber; i >= 0; i--) {
            nextGreaterNumbers[i] = nextGreaterNumber;
            if (isAvailable[i]) {
                nextGreaterNumber = i;
            }
        }
    }
}
