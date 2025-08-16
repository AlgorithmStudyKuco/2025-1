import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1918 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String infix = reader.readLine();
        System.out.println(toPostfix(infix));
    }

    private static Token toPostfix(String infix) {
        if (infix.length() == 1) return new Token(infix);

        LinkedList<Token> tokens = tokenizeCurrentLevel(infix);

        if (tokens.size() == 1) return tokens.getFirst();
        operationToPostfix(tokens, "^[*|/]$");

        if (tokens.size() == 1) return tokens.getFirst();
        operationToPostfix(tokens, "^[+|-]$");

        StringBuilder builder = new StringBuilder();
        for (Token token : tokens) {
            builder.append(token.value);
        }

        return new Token(builder.toString());
    }

    private static LinkedList<Token> tokenizeCurrentLevel(String infix) {
        LinkedList<Token> tokens = new LinkedList<>();

        for (int i = 0; i < infix.length(); i++) {
            char curr = infix.charAt(i);
            if (curr == '(') {
                int index = findPairParenthesis(infix, i);
                tokens.add(toPostfix(infix.substring(i + 1, index)));
                i = index;
            } else {
                tokens.add(toPostfix(String.valueOf(curr)));
            }
        }

        return tokens;
    }

    private static int findPairParenthesis(String infix, int i) {
        int result = i;
        int stack = 1;
        while (stack > 0) {
            result++;
            if (infix.charAt(result) == '(') {
                stack++;
            } else if (infix.charAt(result) == ')') {
                stack--;
            }
        }
        return result;
    }

    private static void operationToPostfix(LinkedList<Token> tokens, String regex) {
        int size = tokens.size();
        for (int i = 0; i < size; i++) {
            tokens.add(tokens.poll());

            if (tokens.get(tokens.size() - 2).value.matches(regex)) {
                Token newToken = new Token(tokens.get(tokens.size() - 3).value
                        + tokens.get(tokens.size() - 1).value
                        + tokens.get(tokens.size() - 2).value);
                tokens.removeLast();
                tokens.removeLast();
                tokens.removeLast();
                tokens.add(newToken);
            }
        }
    }

    private static class Token {
        String value;
        Type type;

        public Token(String value) {
            this.value = value;
            if (value.length() == 1) {
                type = value.matches("^[A-Z]$") ? Type.OPERAND : Type.OPERATOR;
            } else {
                type = Type.EXPRESSION;
            }
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private enum Type {
        OPERATOR, OPERAND, EXPRESSION
    }
}
