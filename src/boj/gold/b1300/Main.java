package boj.gold.b1300;

import java.util.Scanner;

public class Main {

    static int n;
    static int k;

    public static void main(String[] args) {
        input();
        long result = solve();
        System.out.println(result);

    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();
    }

    private static long solve() {
        long left = 0L;
        long right = (long)n * n;
        long result = 0L;

        while (left <= right) {
            long mid = (left + right) / 2;

            long cnt = count(mid);

            if (cnt >= k) {
                right = mid - 1;
                result = mid;
            } else {
                left = mid + 1;
            }

        }
        return result;
    }

    private static long count(long num) {

        long sum = 0L;

        for (int i = 1; i <= n; i++) {
            sum += Math.min(num / i, n);
        }

        return sum;
    }

}
