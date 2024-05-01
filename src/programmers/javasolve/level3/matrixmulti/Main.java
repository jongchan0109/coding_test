package programmers.javasolve.level3.matrixmulti;

class Solution {
    static int[][] dp;
    static int n;

    public int solution(int[][] matrix_sizes) {
        n = matrix_sizes.length;
        dp = new int[n + 1][n + 1];

        return solve(matrix_sizes, 0, n);
    }

    private int solve(int[][] matrix, int start, int end) {
        if (dp[start][end] == 0) {
            calculate(matrix, start, end);
        }
        return dp[start][end];

    }

    private void calculate(int[][] matrix, int start, int end) {
        if (start == end - 1) {
            return;
        }

        int result = Integer.MAX_VALUE;

        for (int i = start + 1; i < end; i++) {
            int left = solve(matrix, start, i);
            int right = solve(matrix, i, end);
            int current = matrix[start][0] * matrix[i][0] * matrix[end - 1][1];
            result = Math.min(result, left + right + current);
        }
        dp[start][end] = result;
    }


}

public class Main {
    public static void main(String[] args) {
        int[][] matrix = {{5, 3}, {3, 10}, {10, 6}};
        int result = new Solution().solution(matrix);
        System.out.println("result = " + result);
    }
}
