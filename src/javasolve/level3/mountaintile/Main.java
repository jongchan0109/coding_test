package javasolve.level3.mountaintile;

class Solution {
    static final int MOD = 10007;

    public int solution(int n, int[] tops) {

        int[][] dp = new int[n + 1][2];

        dp[0][0] = 0; // 오른쪽으로 영향을 미치는 경우
        dp[0][1] = 1; // 오른쪽으로 영향을 미치지 않는 경우 (3가지)

        for (int i = 0; i < n; i++) {
            if (tops[i] == 1) {
                dp[i + 1][1] = (2 * dp[i][0] + 3 * dp[i][1]) % MOD;
            } else {
                dp[i + 1][1] = (dp[i][0] + 2 * dp[i][1]) % MOD;
            }
            dp[i + 1][0] = (dp[i][0] + dp[i][1]) % MOD;
        }
        return (dp[n][0] + dp[n][1]) % MOD;
    }
}

public class Main {
    public static void main(String[] args) {
        int n = 4;
        int[] tops = {1, 1, 0, 1};
        int result = new Solution().solution(n,tops);

        System.out.println("result = " + result);
    }
}
