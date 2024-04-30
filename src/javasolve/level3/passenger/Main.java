package javasolve.level3.passenger;

class Solution {

    final int MOD = 20170805;
    final int WALL = 1;
    final int STRAIGHT = 2;

    final int RIGHT = 0;
    final int DOWN = 1;

    public int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m][n][2];

        for (int i = 0; i < m; i++) {
            if (cityMap[i][0] == WALL) {
                break;
            }
            dp[i][0][DOWN] = 1;
        }

        for (int i = 0; i < n; i++) {
            if (cityMap[0][i] == WALL) {
                break;
            }
            dp[0][i][RIGHT] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (cityMap[i][j] == WALL) {
                    continue;
                }
                // 왼쪽에서 오는 경우
                if (cityMap[i][j - 1] != STRAIGHT) {
                    dp[i][j][RIGHT] = dp[i][j - 1][DOWN];
                }
                dp[i][j][RIGHT] += dp[i][j - 1][RIGHT];
                dp[i][j][RIGHT] %= MOD;

                // 위에서 오는 경우
                if (cityMap[i - 1][j] != STRAIGHT) {
                    dp[i][j][DOWN] = dp[i - 1][j][RIGHT];
                }
                dp[i][j][DOWN] += dp[i - 1][j][DOWN];
                dp[i][j][DOWN] %= MOD;

            }
        }


        return (dp[m - 1][n - 1][DOWN] + dp[m - 1][n - 1][RIGHT]) % MOD;
    }
}

public class Main {
    public static void main(String[] args) {
        int m = 3;
        int n = 6;
        int[][] cityMap = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};
        int answer = new Solution().solution(m, n, cityMap);
        System.out.println("answer = " + answer);
    }
}
