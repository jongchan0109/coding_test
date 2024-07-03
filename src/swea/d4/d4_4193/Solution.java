package swea.d4.d4_4193;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

    public static class Point {
        int x;
        int y;
        int count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    static int N;
    static int[][] board;
    static boolean[][][] visited;
    static int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            visited = new boolean[N][N][3];
            board = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    board[i][j] = sc.nextInt();
                }
            }

            int[] start = new int[2];
            int[] end = new int[2];

            start[0] = sc.nextInt();
            start[1] = sc.nextInt();
            end[0] = sc.nextInt();
            end[1] = sc.nextInt();

            System.out.println("#" + t + " " + bfs(start, end));

        }
    }

    private static int bfs(int[] start, int[] end) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(start[0], start[1], 0));
        visited[start[0]][start[1]][0] = true;

        while (!queue.isEmpty()) {
            Point current = queue.remove();

            if (current.x == end[0] && current.y == end[1]) {
                return current.count;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + move[i][0];
                int nextY = current.y + move[i][1];
                int count = current.count + 1;

                if (range(nextX, nextY) && !visited[nextX][nextY][count % 3]) {
                    if (board[nextX][nextY] == 0) {
                        visited[nextX][nextY][count % 3] = true;
                        queue.add(new Point(nextX, nextY, count));
                    } else if (board[nextX][nextY] == 2) {
                        if (current.count % 3 == 2) {
                            visited[nextX][nextY][count % 3] = true;
                            queue.add(new Point(nextX, nextY, count));
                        } else {
                            visited[current.x][current.y][count % 3] = true;
                            queue.add(new Point(current.x, current.y, count));
                        }
                    }
                }
            }
        }

        return -1;
    }

    private static boolean range(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

}
