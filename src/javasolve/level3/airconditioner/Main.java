package javasolve.level3.airconditioner;

import java.util.Arrays;

class Solution {
    static int MAX = 987654321;

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int answer = MAX;

        int n = onboard.length;

        temperature += 10;
        t1 += 10;
        t2 += 10;

        int[][] dp = new int[n][51];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], MAX);
        }


        dp[0][temperature] = 0;

        //i는 시간, j는 온도

        for (int i = 1; i < n; i++) {
            int start;
            int end;

            if (onboard[i] == 1) {
                start = t1;
                end = t2;
            } else {
                start = Math.min(t1, temperature);
                end = Math.max(t2, temperature);
            }

            for (int j = start; j <= end; j++) {

                if (j >= 1) {
                    // j-1에서 오는 경우
                    if (temperature >= j) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                    } else {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + a);
                    }
                }

                if (j <= 49) {
                    // j+1에서 오는 경우
                    if (temperature > j) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + 1] + a);
                    } else {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + 1]);
                    }
                }

                if (temperature == j) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
                } else {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + b);
                }

            }

        }
        for (int i = 0; i <= 50; i++) {
            answer = Math.min(answer, dp[n - 1][i]);
        }

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        int temperature = 28;
        int t1 = 18;
        int t2 = 26;
        int a = 10;
        int b = 8;
        int[] onboard = {0,0,1,1,1,1,1};

        int result = new Solution().solution(temperature,t1,t2,a,b,onboard);
        System.out.println("result = " + result);

    }
}
