package swea.d4.d4_1227;


import java.util.*;

public class Solution {

    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        for(int test_case = 1; test_case <= 10; test_case++) {
            int T;
            T=sc.nextInt();
            map = new int[100][100];
            visited = new boolean[100][100];

            for (int i = 0; i < 100; i++) {
                String str = sc.next();
                for (int j = 0; j < 100; j++) {
                    map[i][j] = str.charAt(j) - '0';
                }
            }

            int result = BFS();
            System.out.println("#" + T + " " + result);

        }
    }

    public static int BFS() {

        int[] start = new int[2];
        int[] end = new int[2];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] == 2) {
                    start[0] = i;
                    start[1] = j;
                }
                if (map[i][j] == 3) {
                    end[0] = i;
                    end[1] = j;
                }
            }
        }
        visited[start[0]][start[1]] = true;
        queue.add(start);

        while(!queue.isEmpty()) {
            int[] current = queue.remove();

            if (current[0] == end[0] && current[1] == end[1]) {
                return 1;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = current[0] + dx[i];
                int nextY = current[1] + dy[i];
                if (range(nextX, nextY) && !visited[nextX][nextY] && map[nextX][nextY] != 1) {
                    visited[nextX][nextY] = true;
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }
        return 0;
    }

    public static boolean range(int x, int y) {
        return 0 <= x && x < 16 && 0 <= y && y < 16;
    }

}

