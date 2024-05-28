package boj.gold.b2291;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[][][] dp;
    static int n, m, k;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        k = scanner.nextInt();
        scanner.close();

        dp = new int[n + 1][m + 1][m + 1];
        for (int[][] layer : dp) {
            for (int[] row : layer) {
                Arrays.fill(row, -1);
            }
        }

        for (int i = 1; i <= m; i++) {
            dp[n][m][i] = 1;
        }

        solve(0, 0, 1);
        result(0, 0, 1, k);
    }

    static int solve(int len, int sum, int recent) {
        if (len > n || sum > m) {
            return 0;
        }
        if (dp[len][sum][recent] != -1) {
            return dp[len][sum][recent];
        }
        int res = 0;
        for (int i = recent; i <= m; i++) {
            res += solve(len + 1, sum + i, i);
            if (res >= k) {
                res = k;
                break;
            }
        }
        return dp[len][sum][recent] = res;
    }

    static void result(int len, int sum, int recent, int left) {
        if (len == n) {
            return;
        }

        for (int i = recent; i <= m; i++) {
            if (dp[len + 1][sum + i][i] == -1) {
                continue;
            }

            if (left > dp[len + 1][sum + i][i]) {
                left -= dp[len + 1][sum + i][i];
                continue;
            }

            System.out.print(i + " ");
            result(len + 1, sum + i, i, left);
            break;
        }
    }
}
