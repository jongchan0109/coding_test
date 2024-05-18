package swea.d4.d4_1211;

import java.util.Scanner;

public class Solution {

    static int[][] map;
    static int count;
    static int[] dx = {0,0, 1};
    static int[] dy = {1, -1, 0};
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {

            int t = sc.nextInt();
            map = new int[100][100];
            int result = 0;
            int min = Integer.MAX_VALUE;


            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    map[i][j] = sc.nextInt();
                }
            }


            for (int i = 0; i < 100; i++) {
                if (map[0][i] == 1) {
                    visited = new boolean[100][100];
                    count = 0;
                    DFS(0, i, 0);
                    if (count <= min) {
                        min = count;
                        result = i;
                    }
                }
            }

            System.out.println("#" + t + " " + result);

        }
    }

    public static void DFS(int row, int col, int depth) {
        if (row == 99) {
            count = depth;
            return;
        }

        for (int i= 0; i < 3; i++) {
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];

            if (range(nextRow, nextCol) && map[nextRow][nextCol] != 0 && !visited[nextRow][nextCol]) {
                visited[nextRow][nextCol] = true;
                DFS(nextRow, nextCol, depth + 1);
                break;
            }
        }

    }

    public static boolean range(int row, int col) {
        return row >= 0 && row < 100 && col >= 0 && col < 100;
    }

}