package javasolve.level2.rainsnow;

import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        List<Integer> list = new ArrayList<>();
        double[] answer = new double[ranges.length];

        list.add(k);
        while (k != 1) {
            if (k % 2 == 1) {
                k = k * 3 + 1;
            } else {
                k /= 2;
            }
            list.add(k);
        }

        int n = list.size() - 1;

        double[][] dp = new double[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            dp[i][i + 1] = (double) (list.get(i) + list.get(i + 1)) / 2;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= n - i; j++) {
                dp[j][j + i] = dp[j][j + 1] + dp[j + 1][j + i];
            }
        }

        for (int i = 0; i < ranges.length; i++) {
            int a = ranges[i][0];
            int b = n + ranges[i][1];

            if (a > b) {
                answer[i] = -1;
            } else {
                answer[i] = dp[a][b];
            }
        }

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        int k = 5;
        int[][] ranges = {{0, 0}, {0, -1}, {2, -3}, {3, -3}};
        double[] answer = new Solution().solution(k, ranges);

        for (double v : answer) {
            System.out.print(v + " ");
        }
    }
}
