package swea.d4.d4_2819;

import java.util.*;

class Solution
{
    static Set<String> set;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            int[][] map = new int[4][4];
            set = new HashSet<>();

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    DFS(map, new int[]{i,j}, 0, "");
                }
            }
            System.out.println("#" + test_case + " " + set.size());
        }
    }

    public static void DFS(int[][] map, int[] current, int depth, String result) {
        if (depth == 7) {
            set.add(result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = current[0] + dx[i];
            int nextY = current[1] + dy[i];
            if (range(nextX, nextY)) {
                DFS(map, new int[]{nextX, nextY}, depth + 1, result + map[nextX][nextY]);
            }
        }

    }
    public static boolean range(int x, int y) {
        return 0 <= x && x <= 3 && 0 <= y && y <= 3;
    }

}
