import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class BOJ2408 {
    public static void main(String[] args) throws IOException {
        List<String> expression = readInput();

        if (expression == null) return;
        if (expression.size() == 1) {
            System.out.println(expression.get(0));
            return;
        }

        processOperators(expression, "*", "/");

        processOperators(expression, "+", "-");

        printAnswer(expression);
    }

    private static List<String> readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = 2 * Integer.parseInt(reader.readLine()) - 1;
        if (n == -1) return null;

        List<String> expression = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            expression.add(reader.readLine());
        }
        return expression;
    }

    private static void printAnswer(List<String> expression) {
        System.out.println(expression.get(0));
    }

    private static void processOperators(List<String> expression, String... operators) {
        for (int i = 0; i < expression.size(); i++) {
            String curr = expression.get(i);
            if (!isTargetOperator(curr, operators)) continue;

            BigInteger operand1 = new BigInteger(expression.get(i - 1));
            BigInteger operand2 = new BigInteger(expression.get(i + 1));

            String result = calculateOperation(operand1, operand2, curr);

            expression.set(i - 1, result);
            expression.remove(i + 1);
            expression.remove(i);

            i -= 2;
        }
    }

    private static boolean isTargetOperator(String token, String... targetOperators) {
        for (String operator : targetOperators) {
            if (token.equals(operator)) return true;
        }
        return false;
    }

    private static String calculateOperation(BigInteger operand1, BigInteger operand2, String operator) {
        switch (operator) {
            case "*": return operand1.multiply(operand2).toString();
            case "/": return operand1.divide(operand2).toString();
            case "+": return operand1.add(operand2).toString();
            case "-": return operand1.subtract(operand2).toString();
            default: return "";
        }
    }

    private static class BigInteger {
        private static final int PLUS = 1, MINUS = -1;
        private final int sign;
        private final String value;

        public BigInteger(String value) {
            this.sign = (value.startsWith("-") ? MINUS : PLUS);
            this.value = removeLeadingZeros(value.replace("-", ""));
        }

        private BigInteger(String value, int sign) {
            this.sign = sign;
            this.value = removeLeadingZeros(value.replace("-", ""));
        }

        public BigInteger(BigInteger x) {
            this.sign = x.sign;
            this.value = x.value;
        }

        @Override
        public String toString() {
            if (value.equals("0")) return "0";
            return (sign == MINUS ? "-" : "") + value;
        }

        public BigInteger add(BigInteger x) {
            if (sign == MINUS && x.sign == MINUS) {
                return negate(abs(this).add(abs(x)));
            } else if (sign == PLUS && x.sign == MINUS) {
                return abs(this).subtract(abs(x));
            } else if (sign == MINUS && x.sign == PLUS) {
                return abs(x).subtract(abs(this));
            }

            return addImpl(x);
        }

        public BigInteger subtract(BigInteger x) {
            if (sign == MINUS && x.sign == MINUS) {
                return abs(x).subtract(abs(this));
            } else if (sign == PLUS && x.sign == MINUS) {
                return abs(this).add(abs(x));
            } else if (sign == MINUS && x.sign == PLUS) {
                return negate(abs(x).add(abs(this)));
            }

            return subtractImpl(x);
        }

        private BigInteger addImpl(BigInteger x) {
            StringBuilder result = new StringBuilder();
            boolean carry = false;
            for (int i = 0; i < Math.max(value.length(), x.value.length()); i++) {
                int sum = getDigitFromRight(value, i) + getDigitFromRight(x.value, i) + (carry ? 1 : 0);
                carry = sum >= 10;
                result.append(sum % 10);
            }

            if (carry) result.append(1);

            return new BigInteger(result.reverse().toString());
        }

        private BigInteger subtractImpl(BigInteger x) {
            if (abs(x).isGreaterThan(abs(this))) {
                return negate(abs(x).subtract(abs(this)));
            }

            StringBuilder result = new StringBuilder();
            boolean borrowing = false;
            for (int i = 0; i < Math.max(value.length(), x.value.length()); i++) {
                int diff = getDigitFromRight(value, i) - getDigitFromRight(x.value, i) - (borrowing ? 1 : 0);
                borrowing = diff < 0;
                result.append(borrowing ? diff + 10 : diff);
            }

            return new BigInteger(result.reverse().toString());
        }

        public BigInteger multiply(BigInteger x) {
            BigInteger result = BigInteger.valueOf(0);

            for (int i = x.value.length() - 1; i >= 0; i--) {
                String localResult = getLocalMultiplication(x, i);

                result = result.add(new BigInteger(localResult));
            }

            return new BigInteger(result.value, calculateSign(this, x));
        }

        private String getLocalMultiplication(BigInteger x, int i) {
            int carry = 0;
            int[] curr = new int[value.length() + 1];
            for (int j = value.length() - 1; j >= 0; j--) {
                int digit = (value.charAt(j) - '0') * (x.value.charAt(i) - '0');
                digit += carry;
                carry = digit / 10;
                curr[j + 1] = digit % 10;
            }

            StringBuilder currStr = new StringBuilder();
            for (int currChar : curr) {
                currStr.append(currChar);
            }
            if (carry > 0) currStr.setCharAt(0, String.valueOf(carry).charAt(0));

            removeLeadingZeros(currStr);

            int powerOfTen = x.value.length() - 1 - i;
            currStr.append("0".repeat(Math.max(0, powerOfTen)));

            return currStr.toString();
        }

        public BigInteger divide(BigInteger x) {
            if (value.equals("0")) {
                return BigInteger.valueOf(0);
            }

            if (abs(this).isLessThan(abs(x))) {
                if (this.sign == MINUS && x.sign == PLUS || this.sign == PLUS && x.sign == MINUS) {
                    return BigInteger.valueOf(-1);
                }
                return BigInteger.valueOf(0);
            }

            StringBuilder value = new StringBuilder();
            int digitsDiff = this.value.length() - x.value.length();
            if (digitsDiff < 0) return BigInteger.valueOf(0);
            BigInteger remainder = abs(new BigInteger(this));

            while (digitsDiff >= 0) {
                BigInteger base = new BigInteger(x.value + "0".repeat(digitsDiff));

                int count = 0;
                while (remainder.isEqualToOrGreaterThan(base)) {
                    remainder = remainder.subtract(base);
                    count++;
                }
                value.append(count);
                digitsDiff--;
            }

            removeLeadingZeros(value);

            int sign = calculateSign(this, x);
            if (sign == MINUS && !remainder.equals(BigInteger.valueOf(0))) {
                return new BigInteger(new BigInteger(value.toString()).add(BigInteger.valueOf(1)).value, sign);
            }

            return new BigInteger(value.toString(), sign);
        }

        private int getDigitFromRight(String number, int position) {
            if (position >= number.length()) return 0;
            return number.charAt(number.length() - 1 - position) - '0';
        }

        private static int calculateSign(BigInteger a, BigInteger b) {
            return a.sign * b.sign;
        }

        private boolean equals(BigInteger x) {
            return value.equals(x.value);
        }

        private boolean isGreaterThan(BigInteger x) {
            if (sign != x.sign) {
                return sign == PLUS;
            }

            if (value.length() != x.value.length()) {
                return value.length() > x.value.length() ? (sign == PLUS) : (sign == MINUS);
            }

            for (int i = 0; i < value.length(); i++) {
                if (value.charAt(i) != x.value.charAt(i)) {
                    return value.charAt(i) > x.value.charAt(i) ? (sign == PLUS) : (sign == MINUS);
                }
            }

            return false;
        }

        private boolean isEqualToOrGreaterThan(BigInteger x) {
            return this.equals(x) || this.isGreaterThan(x);
        }

        private boolean isLessThan(BigInteger x) {
            return !isEqualToOrGreaterThan(x);
        }

        private String removeLeadingZeros(String str) {
            while (str.length() > 1 && str.charAt(0) == '0') {
                str = str.substring(1);
            }
            return str;
        }

        private void removeLeadingZeros(StringBuilder builder) {
            while (builder.length() > 1 && builder.charAt(0) == '0') {
                builder.deleteCharAt(0);
            }
        }

        private static BigInteger negate(BigInteger x) {
            if (x.value.equals("0")) return x;
            return new BigInteger(x.value, (x.sign == PLUS ? MINUS : PLUS));
        }

        private static BigInteger abs(BigInteger x) {
            return new BigInteger(x.value, PLUS);
        }

        private static BigInteger valueOf(int x) {
            return new BigInteger(String.valueOf(x));
        }
    }
}
