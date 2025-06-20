import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ1092 {
    static int numCrane, numContainer;
    static Integer[] thresholds, weights;

    public static void main(String[] args) throws IOException {
        getInputs();

        Arrays.sort(thresholds, Collections.reverseOrder());
        Arrays.sort(weights, Collections.reverseOrder());

        int[] pointers = getPointers();

        int answer;
        int count = 0;
        boolean[] isShipped = new boolean[numContainer];
        for (answer = 0; answer <= 10000; answer++) {
            if (count == numContainer) {
                break;
            }

            for (int i = 0; i < numCrane; i++) {
                while (pointers[i] != numContainer && isShipped[pointers[i]]) {
                    pointers[i]++;
                }
                if (pointers[i] == numContainer) continue;

                if (validateIfPossibleToShip(pointers, i)) return;

                isShipped[pointers[i]] = true;
                count++;
            }
        }

        System.out.println(answer > 10000 ? -1 : answer);
    }

    private static void getInputs() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        numCrane = Integer.parseInt(reader.readLine());

        thresholds = new Integer[numCrane];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < numCrane; i++) {
            thresholds[i] = Integer.parseInt(tokenizer.nextToken());
        }

        numContainer = Integer.parseInt(reader.readLine());

        tokenizer = new StringTokenizer(reader.readLine());
        weights = new Integer[numContainer];
        for (int i = 0; i < numContainer; i++) {
            weights[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static int[] getPointers() {
        int[] pointers = new int[numCrane];

        int ptr = 0;
        for (int i = 0; i < numCrane; i++) {
            while (ptr < numContainer && thresholds[i] < weights[ptr]) ptr++;
            pointers[i] = ptr;
        }

        return pointers;
    }

    private static boolean validateIfPossibleToShip(int[] pointers, int i) {
        if (weights[pointers[i]] > thresholds[i]) {
            System.out.println(-1);
            return true;
        }

        return false;
    }
}
