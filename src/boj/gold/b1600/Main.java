package boj.gold.b1600;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static class Monkey {
        int x;
        int y;
        int count;
        int jump;

        public Monkey(int x, int y, int jump, int count) {
            this.x = x;
            this.y = y;
            this.jump = jump;
            this.count = count;
        }
    }

    static int k;
    static int w;
    static int h;

    static int[] dx = {-1, 0, 1, 0, -1, -2, -1, -2, 1, 2, 1, 2};
    static int[] dy = {0, 1, 0, -1, 2, 1, -2, -1, 2, 1, -2, -1};

    static int[][] board;

    public static void main(String[] args) {
        input();
        int result = bfs();
        System.out.println(result);
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        k = scanner.nextInt();
        w = scanner.nextInt();
        h = scanner.nextInt();
        board = new int[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                board[i][j] = scanner.nextInt();
            }
        }
    }

    private static int bfs() {
        Queue<Monkey> queue = new LinkedList<>();

        boolean[][][] visited = new boolean[h][w][k + 1];
        queue.add(new Monkey(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {

            Monkey monkey = queue.poll();
            int x = monkey.x;
            int y = monkey.y;
            int jump = monkey.jump;
            int count = monkey.count;

            if (x == h - 1 && y == w - 1) {
                return monkey.count;
            }

            for (int i = 0; i < 12; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (i < 4) {
                    if (range(nextX, nextY) && !visited[nextX][nextY][jump] && board[nextX][nextY] != 1){
                        queue.add(new Monkey(nextX, nextY, jump, count + 1));
                        visited[nextX][nextY][jump] = true;
                    }
                } else if (jump < k){
                    if (range(nextX, nextY) && !visited[nextX][nextY][jump + 1] &&board[nextX][nextY] != 1){
                        queue.add(new Monkey(nextX, nextY, jump + 1, count + 1));
                        visited[nextX][nextY][jump + 1] = true;
                    }
                }
            }

        }
        return -1;
    }

    private static boolean range (int x, int y) {
        return 0 <= x && x < h && 0 <= y && y < w;
    }

}
