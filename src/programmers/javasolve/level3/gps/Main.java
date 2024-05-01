package programmers.javasolve.level3.gps;

import java.util.*;

class Solution {
    public int solution(int n, int[][] edge_list, int k, int[] gps_log) {

        int[][] dp = new int[k+1][n + 1];
        boolean[][] map = new boolean[n+1][n+1];

        for (int[] edge: edge_list) {
            map[edge[0]][edge[1]] = true;
            map[edge[1]][edge[0]] = true;
        }
        for (int i = 1; i <= n; i++) {
            map[i][i] = true;
        }

        for (int i = 1; i <= k ; i++) {
            Arrays.fill(dp[i],k+1);
        }
        dp[1][gps_log[0]] = 0;

        for (int t = 2; t <= k; t++) {
            for (int index = 1; index <= n; index++) {

                for (int i = 1; i<= n; i++) {
                    if (map[index][i]) {
                        dp[t][index] = Math.min(dp[t][index], dp[t-1][i]);
                    }
                }
                if (index != gps_log[t-1]) {
                    dp[t][index]++;
                }
            }
        }

        return dp[k][gps_log[k-1]] >= k + 1 ? -1 : dp[k][gps_log[k-1]];

    }
}

public class Main {
    public static void main(String[] args) {
        int n = 7;
        int[][] edge_list = {{1,2}, {1,3}, {2,3}, {2,4},{3,4},{3,5},{4,6},{5,6},{5,7},{6,7}};
        int k = 6;
        int[] gps_log = {1,2,3,3,6,7};
        int answer = new Solution().solution(n, edge_list,k,gps_log);
        System.out.println("answer = " + answer);
    }
}
