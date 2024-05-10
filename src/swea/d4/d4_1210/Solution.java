package swea.d4.d4_1210;

import java.util.Scanner;

class Solution {

    static int[] dx = {0, 0, -1};
    static int[] dy = {1, -1, 0};
    static boolean[][] visited;
    static int answer;

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);


        for (int test_case = 1; test_case <= 10; test_case++) {
            int T;
            T = sc.nextInt();
            int[][] map = new int[100][100];

            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            int result = 0;
            for (int i =0; i<100; i++) {
                if (map[0][i] == 1) {
                    visited = new boolean[100][100];
                    visited[0][i] = true;
                    dfs(map,0, i);

                    if (answer != -1) {
                        result = i;
                        break;
                    }
                }
            }
            System.out.println("#" + T + " " + result);

        }
    }

    public static void dfs(int[][] map,int a, int b) {

        if (a == 99) {
            if (map[a][b] == 2) {
                answer = 2;
            }
        }

        for (int i=0; i<3; i++) {

            int x = a + dx[i];
            int y = b + dy[i];

            if (x>= 0 && y>=0 && x<100 && y<100) {
                if (!visited[x][y] && map[x][y] == 1 || map[x][y] == 2) {
                    visited[x][y] = true;
                    dfs(map, x,y);
                    break;
                }
            }
        }
    }

}