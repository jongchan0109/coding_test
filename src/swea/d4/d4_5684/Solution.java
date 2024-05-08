package swea.d4.d4_5684;

import java.util.*;

class Solution {

    static final int MAX = 987654321;

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            int n = sc.nextInt();
            int m = sc.nextInt();

            int[][] map = new int[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                Arrays.fill(map[i], 987654321);
            }

            for (int i = 0; i < m; i++) {
                int s = sc.nextInt();
                int e = sc.nextInt();
                int c = sc.nextInt();
                map[s][e] = c;
            }

            for (int i = 1; i <= n; i++) {
                dijkstra(i, map, n);
            }

            int result = MAX;

            for (int i = 1; i <= n; i++) {
                result = Math.min(result, map[i][i]);
            }
            System.out.println("#" + test_case + " " + result);
        }
    }

    public static void dijkstra(int start, int[][] map, int n) {

        int[] d = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(d, MAX);

        for (int i = 1; i <= n; i++) {
            if (map[start][i] != MAX) {
                d[i] = map[start][i];
            }
        }

        for (int i = 1; i <= n; i++) {
            // 아직 방문하지 않은 노드 중에서 가장 거리가 짧은 노드를 찾음
            int minDistance = MAX;
            int minVertex = -1;
            for (int j = 1; j <= n; j++) {
                if (!visited[j] && d[j] <= minDistance) {
                    minDistance = d[j];
                    minVertex = j;
                }
            }

            // 방문한 노드로 표시
            visited[minVertex] = true;

            // 방문한 노드를 통해 갈 수 있는 모든 인접 노드의 거리를 업데이트
            for (int j = 1; j <= n; j++) {
                if (map[minVertex][j] != MAX) {
                    d[j] = Math.min(d[j], d[minVertex] + map[minVertex][j]);
                }
            }
        }

        if (n >= 0) {
            System.arraycopy(d, 1, map[start], 1, n);
        }

    }

}
