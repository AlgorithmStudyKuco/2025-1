import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class BOJ5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numTestcase = Integer.parseInt(reader.readLine());
        for (int t = 0; t < numTestcase; t++) {
            int n = Integer.parseInt(reader.readLine());
            List<String> numbers = new ArrayList<>();
            HashMap<Character, List<String>> phoneBook = new HashMap<>();
            for (int i = 0; i <= 9; i++) {
                phoneBook.put(String.valueOf(i).charAt(0), new ArrayList<>());
            }
            for (int i = 0; i < n; i++) {
                String number = reader.readLine();
                numbers.add(number);
                phoneBook.get(number.charAt(0)).add(number);
            }
            numbers.sort(Comparator.comparingInt(String::length));
            System.out.println(solve(numbers, phoneBook) ? "YES" : "NO");
        }
    }

    private static boolean solve(List<String> numbers, HashMap<Character, List<String>> phoneBook) {
        for (int i = 0; i < numbers.size(); i++) {
            for (String number : phoneBook.get(numbers.get(i).charAt(0))) {
                if (number.length() < numbers.get(i).length() && numbers.get(i).startsWith(number)) return false;
            }
        }
        return true;
    }
}
