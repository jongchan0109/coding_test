package boj.gold.b4811;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static long[][] dp;// 현재 w의 개수, h의 개수

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        dp = new long[31][31];

        for (int i = 0; i < 31; i++) {
            Arrays.fill(dp[i], 0L);
        }

        for (int i = 1; i < 31; i++) {
            dp[i][0] = 1L;
        }

        for (int i = 1; i < 31; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        while (true) {
            int n = scanner.nextInt();

            if (n == 0) {
                break;
            }
            System.out.println(dp[n][n]);
        }



    }


}
