package boj.gold.b9935;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        String explosion = scanner.next();
        scanner.close();

        int explosionLen = explosion.length();
        char lastExplosionChar = explosion.charAt(explosionLen - 1);
        StringBuilder result = new StringBuilder();

        for (char ch : input.toCharArray()) {
            result.append(ch);
            if (result.length() >= explosionLen && result.charAt(result.length() - 1) == lastExplosionChar) {
                boolean isExplosion = true;
                for (int i = 0; i < explosionLen; i++) {
                    if (result.charAt(result.length() - explosionLen + i) != explosion.charAt(i)) {
                        isExplosion = false;
                        break;
                    }
                }
                if (isExplosion) {
                    result.delete(result.length() - explosionLen, result.length());
                }
            }
        }

        if (result.toString().isEmpty()) {
            System.out.println("FRULA");
        } else {
            System.out.println(result);
        }
    }
}
