package swea.d4.d4_1249;

import java.util.*;

class Solution {
    static final int INF = 987654321;
    static final int[] dx = {0,1,0,-1};
    static final int[] dy = {1,0,-1,0};

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int[][] map = new int[n][n];

            for (int i = 0; i < n; i++) {
                String str = sc.next();
                for (int j = 0; j < n; j++) {
                    map[i][j] = str.charAt(j) - '0';
                }
            }
            int result = solution(n, map);
            System.out.println("#" + test_case + " " + result);
        }
    }

    public static int solution(int n, int[][] map) {

        Queue<int[]> queue = new LinkedList<>();

        int[][] d = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(d[i], INF);
        }
        d[0][0] = 0;
        queue.add(new int[]{0,0});

        while(!queue.isEmpty()) {
            int[] current = queue.remove();

            if (current[0] == n - 1 && current[1] == n-1) {
                continue;
            }


            for (int i = 0; i < 4; i++) {
                int nextX = current[0] + dx[i];
                int nextY = current[1] + dy[i];

                if(!range(nextX, nextY, n)) {
                    continue;
                }
                if (d[nextX][nextY] > d[current[0]][current[1]] + map[nextX][nextY]) {
                    queue.add(new int[]{nextX, nextY});
                    d[nextX][nextY] = d[current[0]][current[1]] + map[nextX][nextY];
                }
            }
        }

        return d[n-1][n-1];
    }

    public static boolean range(int x, int y, int n) {
        return 0<= x && x <n && 0 <= y && y < n;
    }

}
