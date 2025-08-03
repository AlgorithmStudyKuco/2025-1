import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17387 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int x1 = Integer.parseInt(tokenizer.nextToken());
        int y1 = Integer.parseInt(tokenizer.nextToken());
        int x2 = Integer.parseInt(tokenizer.nextToken());
        int y2 = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(reader.readLine());
        int x3 = Integer.parseInt(tokenizer.nextToken());
        int y3 = Integer.parseInt(tokenizer.nextToken());
        int x4 = Integer.parseInt(tokenizer.nextToken());
        int y4 = Integer.parseInt(tokenizer.nextToken());

        int ccw1 = crossProduct(x1, y1, x2, y2, x3, y3);
        int ccw2 = crossProduct(x1, y1, x2, y2, x4, y4);
        int ccw3 = crossProduct(x3, y3, x4, y4, x1, y1);
        int ccw4 = crossProduct(x3, y3, x4, y4, x2, y2);

        if (ccw1 == 0 && ccw2 == 0) {
            boolean isXIntersects = Math.max(Math.min(x1, x2), Math.min(x3, x4)) <= Math.min(Math.max(x1, x2), Math.max(x3, x4));
            boolean isYIntersects = Math.max(Math.min(y1, y2), Math.min(y3, y4)) <= Math.min(Math.max(y1, y2), Math.max(y3, y4));

            System.out.println(isXIntersects && isYIntersects ? "1" : "0");
        } else {
            System.out.println(ccw1 * ccw2 <= 0 && ccw3 * ccw4 <= 0 ? "1" : "0");
        }
    }

    private static int crossProduct(int x1, int y1, int x2, int y2, int x3, int y3) {
        long result = (long) (x2 - x1) * (y3 - y1) - (long) (y2 - y1) * (x3 - x1);
        if (result < 0) return -1;
        if (result > 0) return 1;
        return 0;
    }
}
