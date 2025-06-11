import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(tokenizer.nextToken());
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<StackElement> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[i] >= stack.peek().height) stack.pop();
            stack.push(new StackElement(i + 1, heights[i]));

            if (stack.size() == 1) {
                writer.write("0 ");
            } else {
                StackElement temp = stack.pop();
                writer.write(stack.peek().index + " ");
                stack.push(temp);
            }
        }
        writer.flush();
    }

    static class StackElement {
        int index, height;

        public StackElement(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }
}
