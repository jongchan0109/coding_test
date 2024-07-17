package boj.gold.b1016;

import java.util.*;

public class Main {

    static long min;
    static long max;
    static boolean[] check;

    public static void main(String[] args) {
        input();
        long result = solve();
        System.out.println(result);
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        min = scanner.nextLong();
        max = scanner.nextLong();
        check = new boolean[(int)(max - min + 1)];
    }

    private static int solve() {
        int sum = 0;

        for (long i = 2; i * i<= max; i++) { //O(log n)
            long pow = i * i;

            long start = min % pow == 0 ? min / pow: min / pow + 1;

            for (long j = start; j * pow <= max; j++) {
                check[(int)(j * pow - min)] = true;
            }

        }

        for (int i = 0; i < max - min + 1; i++) {
            if (!check[i]) {
                sum++;
            }
        }
        return sum;
    }
}