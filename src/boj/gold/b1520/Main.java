package boj.gold.b1520;

import java.util.Scanner;

public class Main {

    static int[][] board;
    static int[][] dp;
    static int n, m;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        board = new int[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = scanner.nextInt();
                dp[i][j] = -1; // Initialize dp array with -1 (unvisited)
            }
        }

        System.out.println(dfs(0, 0));
    }

    private static int dfs(int x, int y) {
        if (x == n - 1 && y == m - 1) {
            return 1; // Found a valid path
        }

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isValid(nx, ny) && board[nx][ny] < board[x][y]) {
                dp[x][y] += dfs(nx, ny); // Accumulate the number of paths
            }
        }

        return dp[x][y];
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}

