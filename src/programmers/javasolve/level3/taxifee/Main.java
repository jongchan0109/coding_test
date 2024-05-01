package programmers.javasolve.level3.taxifee;

import java.util.*;

class Solution {
    static final int INF = 200000000;
    static int[][] map;
    static boolean[] visited;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        map = new int[n + 1][n + 1];
        visited = new boolean[n + 1];

        initializeMap(n, fares);
        dijkstra(n);

        return calculate(s, a, b, n);
    }

    private void initializeMap(int n, int[][] fares) {
        for (int i = 1; i <= n; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }

        for (int[] fare : fares) {
            map[fare[0]][fare[1]] = fare[2];
            map[fare[1]][fare[0]] = fare[2];
        }
    }

    private void dijkstra(int n) {
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            visited[i] = true;

            for (int k = 1; k <= n; k++) {
                int minIdx = getMinIndex(i, n);

                for (int j = 1; j <= n; j++) {
                    if (map[i][j] > map[i][minIdx] + map[minIdx][j]) {
                        map[i][j] = map[i][minIdx] + map[minIdx][j];
                    }
                }
            }
        }
    }

    private int getMinIndex(int point, int n) {
        int minIdx = n;
        int minDist = INF;

        for (int i = 1; i <= n; i++) {
            if (!visited[i] && map[point][i] < minDist) {
                minDist = map[point][i];
                minIdx = i;
            }
        }
        visited[minIdx] = true;

        return minIdx;
    }

    private int calculate(int s, int a, int b, int n) {
        int minCost = INF;

        for (int i = 1; i <= n; i++) {
            int cost = map[s][i] + map[i][a] + map[i][b];
            minCost = Math.min(cost, minCost);
        }

        return minCost;
    }
}


public class Main {
    public static void main(String[] args) {
        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        int result = new Solution().solution(n, s, a, b, fares);
        System.out.println("result = " + result);
    }
}
