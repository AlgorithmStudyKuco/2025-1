import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2166 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] x = new int[n + 1];
        int[] y = new int[n + 1];

        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            x[i] = Integer.parseInt(tokenizer.nextToken());
            y[i] = Integer.parseInt(tokenizer.nextToken());
        }
        x[n] = x[0]; y[n] = y[0];

        long count = 0;
        for (int i = 0; i < n; i++) {
            count += ((long) x[i] * y[i+1] - (long) x[i + 1] * y[i]);
        }

        count = Math.abs(count) * 5;

        String area = String.valueOf(count);
        if (area.length() == 1)  {
            System.out.println("0." + area.charAt(0));
            return;
        }

        for (int i = 0; i < area.length() - 1; i++) {
            System.out.print(area.charAt(i));
        }
        System.out.println("." + area.charAt(area.length() - 1));
    }
}
