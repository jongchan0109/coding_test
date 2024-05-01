package programmers.javasolve.level3.countdown;

class Solution {
    public int[] solution(int target) {
        int[] answer = new int[2];
        int[][] dp = new int[100001][2];

        for (int i = 1; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= 20; i++) {
            dp[i*2][0] = 1;
            dp[i*2][1] = 0;
            dp[i*3][0] = 1;
            dp[i*3][1] = 0;
        }

        for (int i = 1; i <= 20 ; i++) {
            dp[i][0] = 1;
            dp[i][1] = 1;
        }
        dp[50][0] = 1;
        dp[50][1] = 1;



        for (int i = 1; i <= target; i++) {
            for (int j = 1; j <= i/2 ; j++) {
                if (dp[j][0] + dp[i-j][0] < dp[i][0]) {
                    dp[i][0] = dp[j][0] + dp[i-j][0];
                    dp[i][1] = dp[j][1] + dp[i-j][1];
                } else if(dp[j][0] + dp[i-j][0] == dp[i][0]) {
                    if (dp[j][1] + dp[i-j][1] > dp[i][1]) {
                        dp[i][1] = dp[j][1] + dp[i-j][1];
                    }
                }
            }
        }

        answer[0] = dp[target][0];
        answer[1] = dp[target][1];

        return answer;
    }

}
public class Main {
    public static void main(String[] args) {
        int target = 21;
        int[] result = new Solution().solution(target);

        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
