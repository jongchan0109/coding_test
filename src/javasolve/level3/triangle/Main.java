package javasolve.level3.triangle;

class Solution {
    public int solution(int[][] triangle) {
        int[][] dp = new int[triangle.length][triangle.length];
        dp[0][0] = triangle[0][0];

        for (int i = 1; i < triangle.length; i++) {
            dp[i][0] = dp[i - 1][0] + triangle[i][0];
            int n = triangle[i].length;

            for (int j = 1; j < n - 1; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
            }
            dp[i][n - 1] = dp[i - 1][i - 1] + triangle[i][n - 1];


        }
        int answer = 0;

        for (int i = 0; i < triangle.length; i++) {
            answer = Math.max(answer, dp[dp.length - 1][i]);
        }

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        int result = new Solution().solution(triangle);
        System.out.println("result = " + result);
    }
}
