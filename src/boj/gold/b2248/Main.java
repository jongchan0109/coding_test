package boj.gold.b2248;

import java.util.Scanner;

public class Main {

    static int N;
    static int L;
    static long l;
    static int[][] dp;
    static StringBuilder sb;


    public static void main(String[] args) {

        input();
        dp = new int[33][33];
        sb = new StringBuilder();

        combination();

        solve(N, L, l);
        System.out.println(sb);

    }

    private static void solve(int depth, int count, long sequence) {

        if (depth == 0) {
            return;
        }
        if (count == 0) {
            sb.append("0".repeat(Math.max(0, depth)));
            return;
        }

        long sum = 0;

        for (int i = 0; i <= count; i++) {
            sum += dp[depth - 1][i];
        }

        if (sum >= sequence) {
            sb.append("0");
            solve(depth - 1, count, sequence);
        } else {
            sb.append("1");
            solve(depth - 1, count - 1, sequence - sum);
        }
    }

    private static void combination() {

        dp[0][0] = 1;
        for (int i = 1; i < 33; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= L; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
    }


    private static void input() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        L = scanner.nextInt();
        l = scanner.nextLong();
    }

}



