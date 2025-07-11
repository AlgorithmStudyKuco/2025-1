import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2064 {
    static final int BYTE = 8;

    public static void main(String[] args) throws IOException {
        int[] addresses = initialize();

        int netMask = 0xFFFFFFFF;
        LOOP: for (int i = 0; i < 32; i++) {
            for (int j = 1; j < addresses.length; j++) {
                if ((addresses[0] & netMask) != (addresses[j] & netMask)) {
                    netMask <<= 1;
                    continue LOOP;
                }
            }
            break;
        }

        System.out.println(parse(addresses[0] & netMask));
        System.out.println(parse(netMask));
    }

    private static int[] initialize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] addresses = new int[n];
        for (int i = 0; i < n; i++) {
            addresses[i] = parse(reader.readLine());
        }

        return addresses;
    }

    private static int parse(String address) {
        int result = 0;
        int[] octets = Arrays.stream(address.split("\\."))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int i = 0; i < 4; i++) {
            result |= octets[i] << ((3 - i) * BYTE);
        }

        return result;
    }

    private static String parse(int address) {
        StringBuilder result = new StringBuilder();
        int mask = 0x000000FF;
        for (int i = 0; i < 4; i++) {
            result.append((address >> ((3 - i) * BYTE)) & mask).append(i != 3 ? "." : "");
        }

        return result.toString();
    }
}