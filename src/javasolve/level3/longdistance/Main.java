package javasolve.level3.longdistance;

import java.util.*;

class Solution {
    static short[][] map;
    static final short INF = 20000;

    public int solution(int n, int[][] edge) {
        map = new short[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }

        for (int[] e : edge) {
            map[e[0]][e[1]] = 1;
            map[e[1]][e[0]] = 1;
        }

        dijkstra(n);

        return count(n);
    }

    int count(int n) {
        int result = 0;
        int max = 0;

        for (int i = 2; i <= n; i++) {
            if (map[1][i] < INF) {
                max = Math.max(max, map[1][i]);
            }
        }

        for (int i = 2; i <= n; i++) {
            if (map[1][i] == max) {
                result++;
            }
        }
        return result;
    }

    void dijkstra(int n) {
        boolean[] visited = new boolean[n + 1];
        short[] distance = new short[n + 1];
        Arrays.fill(distance, INF);
        distance[1] = 0;

        for (int i = 1; i <= n; i++) {
            int minIdx = -1;
            int minDist = INF;

            for (int j = 1; j <= n; j++) {
                if (!visited[j] && distance[j] < minDist) {
                    minIdx = j;
                    minDist = distance[j];
                }
            }

            visited[minIdx] = true;

            for (int j = 1; j <= n; j++) {
                if (!visited[j] && map[minIdx][j] != INF) {
                    distance[j] = (short)Math.min(distance[j], distance[minIdx] + map[minIdx][j]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            map[1][i] = distance[i];
            map[i][1] = distance[i];
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int n = 6;
        int[][] vertex = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        int result = new Solution().solution(n, vertex);
        System.out.println("result = " + result);
    }
}
