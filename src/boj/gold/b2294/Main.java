package boj.gold.b2294;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int n;
    static int k;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) {

        init();
        int result = solve();

        System.out.println(result);
    }

    private static int solve() {
        dp[0] = 0;
        dp[arr[0]] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k - arr[i]; j++) {
                if (dp[j] == Integer.MAX_VALUE) {
                    continue;
                }
                dp[j + arr[i]] = Math.min(dp[j + arr[i]], dp[j] + 1);
            }
        }


        return dp[k] == Integer.MAX_VALUE ? -1 : dp[k];
    }

    private static void init() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        dp = new int[k+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
    }

}
