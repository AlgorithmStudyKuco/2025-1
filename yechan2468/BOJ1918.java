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
        if (infix.length() == 1) {
            return new Token(infix);
        }

        LinkedList<Token> tokens = tokenizeCurrentLevel(infix);

        if (tokens.size() == 1) {
            return tokens.getFirst();
        }

        operationToPostfix(tokens, "^[*|/]$");

        if (tokens.size() == 1) {
            return tokens.getFirst();
        }

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
                int lastIndex = i;
                int stack = 1;
                while (stack > 0) {
                    lastIndex++;
                    if (infix.charAt(lastIndex) == '(') {
                        stack++;
                    } else if (infix.charAt(lastIndex) == ')') {
                        stack--;
                    }
                }
                tokens.add(toPostfix(infix.substring(i + 1, lastIndex)));
                i = lastIndex;
            } else {
                tokens.add(toPostfix(String.valueOf(curr)));
            }
        }

        return tokens;
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
