package programmers.javasolve.level3.avantgarde;

class Solution {

    static final int MOD = 1000000007;

    public int solution(int n) {

        long[] dp = new long[100001];
        dp[0] = 1L;
        dp[1] = 1L;
        dp[2] = 3L;
        dp[3] = 10L;
        dp[4] = 23L;
        dp[5] = 62L;
        dp[6] = 170L;


        for (int i = 7; i <= n ; i++) {
            dp[i] = (dp[i-1] + 2 * dp[i-2] + 6 * dp[i-3] + dp[i-4] - dp[i-6] + MOD) % MOD;
        }


        return (int)dp[n];
    }
}

public class Main {
    public static void main(String[] args) {
        int n = 10;
        int result = new Solution().solution(n);

        System.out.println("result = " + result);
    }
}
