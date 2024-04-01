package javasolve.level3.goschool;

class Solution {

    static final int MAX = 1000000007;

    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[m + 1][n + 1];

        for (int[] puddle : puddles) {
            dp[puddle[0]][puddle[1]] = -1;
        }

        for (int i = 1; i <= n; i++) {
            if (dp[1][i] == -1) {
                break;
            }
            dp[1][i] = 1;
        }

        for (int i = 1; i <= m; i++) {
            if (dp[i][1] == -1) {
                break;
            }
            dp[i][1] = 1;
        }


        for (int i = 2; i <= m; i++) {
            for (int j = 2; j <= n; j++) {
                if (dp[i][j] == -1) {
                    continue;
                }
                if (dp[i - 1][j] != -1) {
                    dp[i][j] += dp[i - 1][j];
                }
                if (dp[i][j - 1] != -1) {
                    dp[i][j] += dp[i][j - 1];
                }
                dp[i][j] %= MAX;

            }
        }

        return dp[m][n];
    }
}

public class Main {
    public static void main(String[] args) {
        int m = 4;
        int n = 3;
        int[][] puddles = {{2, 2}};
        int result = new Solution().solution(m, n, puddles);
        System.out.println("result = " + result);
    }
}
