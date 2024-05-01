package programmers.javasolve.level3.subsequence;

class Solution {
    public long solution(int[] sequence) {
        int n = sequence.length;

        // [i][0] - i번째 값이 +, [i][1] - i번째 값이 -
        long[][] dp = new long[n][2];
        dp[0][0] = sequence[0];
        dp[0][1] = -1 * sequence[0];

        long answer = Math.max(dp[0][0], dp[0][1]);

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][1] + sequence[i], sequence[i]); // 이전의 최대값과 더하거나 자기부터 시작
            dp[i][1] = Math.max(dp[i - 1][0] - sequence[i], -sequence[i]);
            answer = Math.max(answer, dp[i][0]);
            answer = Math.max(answer, dp[i][1]);
        }

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] sequence = {2, 3, -6, 1, 3, -1, 2, 4};
        long result = new Solution().solution(sequence);

        System.out.println("result = " + result);
    }
}
