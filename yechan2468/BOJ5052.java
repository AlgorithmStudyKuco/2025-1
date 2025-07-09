import java.io.*;
import java.util.*;

public class BOJ5052 {
    static List<String> numbers;
    static HashMap<Character, List<String>> phoneBook;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numTestcase = Integer.parseInt(reader.readLine());
        for (int t = 0; t < numTestcase; t++) {
            initialize(reader);
            System.out.println(solve(numbers, phoneBook) ? "YES" : "NO");
        }
    }

    private static boolean solve(List<String> numbers, HashMap<Character, List<String>> phoneBook) {
        for (String number1 : numbers) {
            for (String number2 : phoneBook.get(number1.charAt(0))) {
                if (number2.length() < number1.length() && number1.startsWith(number2)) return false;
            }
        }
        return true;
    }

    private static void initialize(BufferedReader reader) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        numbers = new ArrayList<>();
        phoneBook = new HashMap<>();
        for (int i = 0; i <= 9; i++) {
            phoneBook.put(String.valueOf(i).charAt(0), new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            String number = reader.readLine();
            numbers.add(number);
            phoneBook.get(number.charAt(0)).add(number);
        }
        numbers.sort(Comparator.comparingInt(String::length));
    }
}
