import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1493 {
    static int length, width, height;
    static int numCube;
    static List<Cube> cubes;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        initialize();

        boolean result = solve(length, width, height);

        System.out.println(result ? answer : -1);
    }

    private static boolean solve(int length, int width, int height) {
        if (length == 0 || width == 0 || height == 0) return true;

        for (int i = numCube - 1; i >= 0; i--) {
            int count = cubes.get(i).count, size = cubes.get(i).size;
            if (count == 0 || size > length || size > width || size > height) continue;

            answer++;
            cubes.get(i).count--;

            return solve(length - size, width, height)
                    && solve(size, width - size, height)
                    && solve(size, size, height - size);
        }

        return false;
    }

    private static void initialize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        length = Integer.parseInt(tokenizer.nextToken());
        width = Integer.parseInt(tokenizer.nextToken());
        height = Integer.parseInt(tokenizer.nextToken());
        numCube = Integer.parseInt(reader.readLine());
        cubes = new ArrayList<>();
        for (int i = 0; i < numCube; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int power = Integer.parseInt(tokenizer.nextToken());
            int count = Integer.parseInt(tokenizer.nextToken());
            cubes.add(new Cube(power, count));
        }
    }

    static class Cube {
        int size = 1, count;

        public Cube(int power, int count) {
            for (int i = 0; i < power; i++) {
                size *= 2;
            }
            this.count = count;
        }
    }
}
