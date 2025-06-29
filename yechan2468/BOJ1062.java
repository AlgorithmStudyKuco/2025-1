import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1062 {
    static int numWord, numAlphabet;
    static String[] words;
    static List<Character> alphabets;
    static Hashtable<Character, Boolean> isAlphabetUsed;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        getInputs();

        if (numAlphabet < 5) {
            System.out.println(0);
            return;
        }
        if (numAlphabet >= alphabets.size()) {
            System.out.println(numWord);
            return;
        }
        numAlphabet -= 5;

        solve(0, -1);

        System.out.println(answer);
    }

    static void solve(int depth, int prevIdx) {
        int count = 0;
        if (depth == numAlphabet) {
            for (String word : words) {
                if (word.isEmpty()) continue;

                boolean readable = true;
                for (char ch : word.toCharArray()) {
                    if (!isAlphabetUsed.getOrDefault(ch, false)) {
                        readable = false;
                        break;
                    }
                }
                if (readable) count++;
            }

            answer = Math.max(answer, count);
            return;
        }

        for (int i = prevIdx + 1; i < alphabets.size(); i++) {
            char alphabet = alphabets.get(i);
            if (isAlphabetUsed.getOrDefault(alphabet, false)) continue;

            isAlphabetUsed.put(alphabet, true);
            solve(depth+1, i);
            isAlphabetUsed.put(alphabet, false);
        }
    }

    private static void getInputs() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        numWord = Integer.parseInt(tokenizer.nextToken());
        numAlphabet = Integer.parseInt(tokenizer.nextToken());
        words = new String[numWord];
        HashSet<Character> alphabetsSet = new HashSet<>();
        for (int i = 0; i < numWord; i++) {
            words[i] = reader.readLine();
            for (int j = 0; j < words[i].length(); j++) {
                alphabetsSet.add(words[i].charAt(j));
            }
        }
        alphabets = new ArrayList<>(alphabetsSet);
        isAlphabetUsed = new Hashtable<>();
        for (char ch : "acint".toCharArray()) {
            isAlphabetUsed.put(ch, true);
        }
    }
}
