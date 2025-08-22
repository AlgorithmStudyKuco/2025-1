import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

public class BOJ16934 {
    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static int n;
    private static HashMap<String, Integer> numberedNicknames;
    private static HashMap<Integer, HashMap<Character, HashSet<String>>> aliases, nicknames;

    public static void main(String[] args) throws IOException {
        initialize();

        for (int i = 0; i < n; i++) {
            String nickname = reader.readLine();

            if (nicknames.get(nickname.length()).get(nickname.charAt(0)).contains(nickname)) {
                numberNickname(nickname);
                continue;
            }
            nicknames.get(nickname.length()).get(nickname.charAt(0)).add(nickname);

            writer.write(getPossibleAlias(nickname) + "\n");

            registerPrefixes(nickname);
        }

        writer.flush();
    }

    private static void numberNickname(String nickname) throws IOException {
        numberedNicknames.putIfAbsent(nickname, 1);
        int number = numberedNicknames.get(nickname) + 1;
        numberedNicknames.put(nickname, number);
        writer.write(nickname + number + "\n");
    }

    private static StringBuilder getPossibleAlias(String nickname) {
        StringBuilder alias = new StringBuilder();
        for (int j = 0; j < nickname.length(); j++) {
            alias.append(nickname.charAt(j));
            String aliasString = alias.toString();
            if (!aliases.get(aliasString.length()).get(aliasString.charAt(0)).contains(aliasString)) break;
        }
        return alias;
    }

    private static void registerPrefixes(String nickname) {
        StringBuilder alias;
        alias = new StringBuilder();
        for (int j = 0; j < nickname.length(); j++) {
            alias.append(nickname.charAt(j));
            String aliasString = alias.toString();
            aliases.get(aliasString.length()).get(aliasString.charAt(0)).add(aliasString);
        }
    }

    private static void initialize() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
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
