package swea.d4.d4_1868;

import java.util.*;

public class Solution {

    static char[][] map;
    static int[] dx = {0, 1, 0, -1, -1, -1, 1, 1};
    static int[] dy = {1, 0, -1, 0, -1, 1, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();


        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            map = new char[n][n];
            visited = new boolean[n][n];
            int result = 0;

            for (int i = 0; i < n; i++) {
                String str = sc.next();
                for (int j = 0; j < n; j++) {
                    map[i][j] = str.charAt(j);
                }
            }


            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == '.') {
                        if (mine(i, j, n) == 0) {
                            destroy(i, j, n);
                            result++;
                        }
                    }
                }
            }


            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == '.') {
                        result++;
                    }
                }
            }

            System.out.println("#" + test_case + " " + result);

        }
    }

    private static void destroy(int i, int j, int n) {

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});

        map[i][j] = '0';
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.remove();

            for (int k = 0; k < 8; k++) {
                int nextX = current[0] + dx[k];
                int nextY = current[1] + dy[k];

                if (range(nextX, nextY, n)) {
                    map[nextX][nextY] = (char) (mine(nextX, nextY, n) + '0');
                    if (map[nextX][nextY] == '0' && !visited[nextX][nextY]) {
                        visited[nextX][nextY] = true;
                        queue.add(new int[]{nextX, nextY});
                    }
                }
            }

        }

    }

    public static int mine(int x, int y, int n) {

        int count = 0;

        for (int i = 0; i < 8; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (range(nextX, nextY, n) && map[nextX][nextY] == '*') {
                count++;
            }
        }
        return count;
    }

    public static boolean range(int x, int y, int n) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }


}
