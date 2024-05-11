package swea.d4.d4_1210;

import java.util.Scanner;

public class Solution {
    static int[] dx = {0, 0, 1};
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

            for (int i = 0; i < 100; i++) {
                if (map[0][i] == 1) {
                    visited = new boolean[100][100];
                    dfs(map, 0, i);
                    if (answer == 2) {
                        System.out.println("#" + T + " " + i);
                        break;
                    }
                }
            }
        }
    }

    public static void dfs(int[][] map, int row, int col) {

        if (row == 99) {
            answer = map[row][col];
        }

        for (int i = 0; i < 3; i++) {
            int x = row + dx[i];
            int y = col + dy[i];

            if (0 <= x && x <= 99 && 0 <= y && y <= 99 && (map[x][y] == 1 || map[x][y] == 2) && !visited[x][y]) {
                visited[x][y] = true;
                dfs(map, x, y);
                break;
            }

        }

    }

}