package boj.gold.b1103;

import java.util.Scanner;

public class Main {

    static int n;
    static int m;
    static String[] board;

    static int max = 0;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static boolean[][] visited;
    static int[][] dp;

    static boolean infiniteLoop = false;

    public static void main(String[] args) {
        input();

        visited[0][0] = true;
        dfs(0, 0, 0);

        if (infiniteLoop) {
            System.out.println(-1);
        } else {
            System.out.println(max);
        }
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        board = new String[n];
        visited = new boolean[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            board[i] = scanner.next();
        }
    }

    private static void dfs(int x, int y, int count) {
        if (infiniteLoop) {
            return;
        }

        dp[x][y] = count;

        int boardNum = board[x].charAt(y) - '0';

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i] * boardNum;
            int nextY = y + dy[i] * boardNum;

            if (!range(nextX, nextY) || board[nextX].charAt(nextY) == 'H') {
                max = Math.max(max, count + 1);
                continue;
            }

            if (visited[nextX][nextY]) {
                infiniteLoop = true;
                return;
            }

            if (dp[nextX][nextY] > count) {
                continue;
            }

            visited[nextX][nextY] = true;
            dfs(nextX, nextY, count + 1);
            visited[nextX][nextY] = false;
        }

    }

    private static boolean range(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}