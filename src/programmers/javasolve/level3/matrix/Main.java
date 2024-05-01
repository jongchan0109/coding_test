package programmers.javasolve.level3.matrix;

class Solution {
    public int[] solution(int e, int[] starts) {
        int min = 0;
        int n = starts.length;
        int[] answer = new int[n];
        int[][] dp = new int[e + 1][2];


        for (int s : starts) {
            if (min > s) {
                min = s;
            }
        }
        int[] divisor = divisor(e);
        dp[e][0] = e;
        dp[e][1] = divisor[e];

        for (int i = e - 1; i >= min; i--) {
            if (divisor[i] >= dp[i + 1][1]) {
                dp[i][0] = i;
                dp[i][1] = divisor[i];
            } else {
                dp[i][0] = dp[i+1][0];
                dp[i][1] = dp[i + 1][1];
            }
        }

        for (int i = 0; i < n; i++) {
            answer[i] = dp[starts[i]][0];
        }
        return answer;

    }

    public int[] divisor(int e) {
        int[] divide = new int[e+1];

        for (int i = 1; i <= e; i++) {
            for (int j = 1; j <= e/i ; j++) {
                divide[i*j]++;
            }
        }
        return divide;
    }
}

public class Main {
    public static void main(String[] args) {
        int e = 8;
        int[] starts = {1, 3, 7};
        int[] result = new Solution().solution(e, starts);

        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
