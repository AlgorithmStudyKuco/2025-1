import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ10775 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numGate = Integer.parseInt(reader.readLine());
        int numPlane = Integer.parseInt(reader.readLine());
        int[] availableGates = new int[numPlane + 1];
        for (int i = 1; i <= numPlane; i++) {
            availableGates[i] = Integer.parseInt(reader.readLine());
        }
        int answer = 0;

        Arrays.sort(availableGates);

        int maxAvailableGate = numGate;
        for (int i = numPlane; i > 0; i--) {
            if (maxAvailableGate == 0) break;
            if (availableGates[i] <= maxAvailableGate) {
                answer++;
                maxAvailableGate = availableGates[i] - 1;
            }
        }

        System.out.println(answer);
    }
}