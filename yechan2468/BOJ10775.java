import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class BOJ10775 {
    static int numGate, numPlane, answer;
    static int[] availableGates;
    static TreeSet<Integer> bst;

    public static void main(String[] args) throws IOException {
        initialize();

        solve();

        System.out.println(answer);
    }

    public static void solve() {
        for (int i = 0; i < numPlane; i++) {
            if (bst.isEmpty()) return;
            Integer searchResult = bst.floor(availableGates[i]);
            if (searchResult == null) return;
            bst.remove(searchResult);
            answer++;
        }
    }

    private static void initialize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        numGate = Integer.parseInt(reader.readLine());
        numPlane = Integer.parseInt(reader.readLine());
        availableGates = new int[numPlane];
        for (int i = 0; i < numPlane; i++) {
            availableGates[i] = Integer.parseInt(reader.readLine()) - 1;
        }
        bst = new TreeSet<>();
        for (int i = 0; i < numGate; i++) {
            bst.add(i);
        }
        answer = 0;
    }
}