import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class BOJ9375 {
    static BufferedReader reader;
    static StringTokenizer tokenizer;
    static int answer = 1;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        int numTestcases = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numTestcases; i++) {
            solve();
            System.out.println(answer);
            answer = 1;
        }
    }

    static void solve() throws IOException {
        int numClothes = Integer.parseInt(reader.readLine());

        Hashtable<String, Integer> categoryIds = new Hashtable<>();
        int newCategoryId = 0;
        int[] clothesCounts = new int[numClothes];

        for (int i = 0; i < numClothes; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String cloth = tokenizer.nextToken();
            String category = tokenizer.nextToken();
            if (categoryIds.containsKey(category)) {
                clothesCounts[categoryIds.get(category)]++;
            } else {
                categoryIds.put(category, newCategoryId);
                clothesCounts[newCategoryId]++;
                newCategoryId++;
            }
        }

        for (int i = 0; i < newCategoryId; i++) {
            answer *= (clothesCounts[i] + 1);
        }
        answer--;
    }
}
