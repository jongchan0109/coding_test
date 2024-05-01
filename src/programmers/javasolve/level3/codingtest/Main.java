package programmers.javasolve.level3.codingtest;


class Solution {


    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0;
        int maxCop = 0;

        for (int[] problem : problems) {
            if (problem[0] > maxAlp) {
                maxAlp = problem[0];
            }
            if (problem[1] > maxCop) {
                maxCop = problem[1];
            }
        }

        if (maxAlp <= alp && maxCop <= cop) {
            return 0;
        } else if (maxAlp <= alp) {
            maxAlp = alp;
        } else if (maxCop <= cop) {
            maxCop = cop;
        }


        int[][] dp = new int[maxAlp + 2][maxCop + 2];

        for (int i = 0; i <= maxAlp; i++) {
            for (int j = 0; j <= maxCop; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[alp][cop] = 0;

        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);


                for (int[] problem : problems) {
                    if (i >= problem[0] && j >= problem[1]) {
                        if (i + problem[2] > maxAlp && j + problem[3] > maxCop) {
                            dp[maxAlp][maxCop] = Math.min(dp[maxAlp][maxCop], dp[i][j] + problem[4]);
                        } else if (i + problem[2] > maxAlp) {
                            dp[maxAlp][j + problem[3]] = Math.min(dp[maxAlp][j + problem[3]], dp[i][j] + problem[4]);
                        } else if (j + problem[3] > maxCop) {
                            dp[i + problem[2]][maxCop] = Math.min(dp[i + problem[2]][maxCop], dp[i][j] + problem[4]);
                        } else {
                            dp[i + problem[2]][j + problem[3]] = Math.min(dp[i + problem[2]][j + problem[3]], dp[i][j] + problem[4]);
                        }


                    }
                }

            }
        }

        return dp[maxAlp][maxCop];
    }


}

public class Main {
    public static void main(String[] args) {
        int alp = 10;
        int cop = 10;
        int[][] problems = {{10,15,2,1,2}, {20,20,3,3,4}};
        int result = new Solution().solution(alp,cop,problems);
        System.out.println("result = " + result);
    }
}
