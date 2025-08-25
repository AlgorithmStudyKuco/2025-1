import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

public class BOJ16934 {
    private static int n;
    private static HashMap<String, Integer> numberedNicknames;
    private static HashMap<Integer, HashMap<Character, HashSet<String>>> aliases, nicknames;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize(reader);

        for (int i = 0; i < n; i++) {
            String nickname = reader.readLine();
            solve(nickname, writer);
        }

        writer.flush();
    }

    private static void solve(String nickname, BufferedWriter writer) throws IOException {
        if (isExist(nicknames, nickname)) {
            writer.write(getNumberedNickname(nickname) + "\n");
            return;
        }
        register(nicknames, nickname);

        writer.write(getPossibleAlias(nickname) + "\n");

        registerPrefixes(nickname);
    }

    private static String getNumberedNickname(String nickname) {
        numberedNicknames.putIfAbsent(nickname, 1);
        int number = numberedNicknames.get(nickname) + 1;
        numberedNicknames.put(nickname, number);
        return nickname + number;
    }

    private static StringBuilder getPossibleAlias(String nickname) {
        StringBuilder alias = new StringBuilder();
        for (int j = 0; j < nickname.length(); j++) {
            alias.append(nickname.charAt(j));
            if (!isExist(aliases, alias.toString())) break;
        }
        return alias;
    }

    private static void registerPrefixes(String nickname) {
        StringBuilder alias = new StringBuilder();
        for (int j = 0; j < nickname.length(); j++) {
            alias.append(nickname.charAt(j));
            register(aliases, alias.toString());
        }
    }

    private static boolean isExist(HashMap<Integer, HashMap<Character, HashSet<String>>> names, String name) {
        return names.get(name.length()).get(name.charAt(0)).contains(name);
    }

    private static void register(HashMap<Integer, HashMap<Character, HashSet<String>>> names, String name) {
        names.get(name.length()).get(name.charAt(0)).add(name);
    }

    private static void initialize(BufferedReader reader) throws IOException {
        n = Integer.parseInt(reader.readLine());
        aliases = new HashMap<>();
        nicknames = new HashMap<>();
        for (int i = 0; i <= 10; i++) {
            aliases.put(i, new HashMap<>());
            nicknames.put(i, new HashMap<>());
            for (char j = 'a'; j <= 'z'; j++) {
                aliases.get(i).put(j, new HashSet<>());
                nicknames.get(i).put(j, new HashSet<>());
            }
        }
        numberedNicknames = new HashMap<>();
    }
}
