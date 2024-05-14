package swea.d4.d4_1861;

import java.util.*;

public class Solution {

    static int[][] map;
    static int max;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();


        for(int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            map = new int[n][n];
            max = 0;
            int answer = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int result = BFS(i, j, n);
                    if (result > max) {
                        max = result;
                        answer = map[i][j];
                    } else if(result == max) {
                        answer = Math.min(answer, map[i][j]);
                    }
                }
            }

            System.out.println("#" + test_case + " " + answer + " " + max);

        }
    }

    public static int BFS(int i, int j, int n) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i,j});
        int sum = 1;

        while(!queue.isEmpty()) {
            int[] current = queue.remove();

            for (int d = 0; d < 4; d++) {
                int nextX = current[0] + dx[d];
                int nextY = current[1] + dy[d];

                if (range(nextX, nextY, n)) {
                    if (map[nextX][nextY] == map[current[0]][current[1]] + 1) {
                        queue.add(new int[]{nextX, nextY});
                        sum++;
                        break;
                    }
                }
            }
        }
        return sum;
    }

    public static boolean range(int x, int y, int n) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

}
