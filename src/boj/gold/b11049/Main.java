package boj.gold.b11049;

import java.util.Scanner;

public class Main {

    static int N;
    static int[][] matrix;
    static int[][] dp;

    public static void main(String[] args) {
        input();
        System.out.println(solve());
    }

    private static int solve() {

        for (int i = 0; i < N - 1; i++) {
            dp[i][i+1] = matrix[i][0] * matrix[i][1] * matrix[i+1][1];
        }


        for (int len = 2; len < N; len++) {
            for (int start = 0; start < N - len; start++) {
                dp[start][start + len] = Integer.MAX_VALUE;
                for (int mid = start; mid < len + start; mid++) {
                    int value = dp[start][mid] + dp[mid + 1][start + len] + matrix[start][0] * matrix[mid][1] * matrix[start + len][1];
                    dp[start][start+len] = Math.min(dp[start][start+len],value);
                }
            }
        }



        return dp[0][N-1];
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        matrix = new int[N][2];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            matrix[i][0] = scanner.nextInt();
            matrix[i][1] = scanner.nextInt();
        }

    }

}
