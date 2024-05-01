package programmers.javasolve.level3.roadconstruction;

class Solution {

    static int[][] map;
    static int min = Integer.MAX_VALUE;
    static int[][] move = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    static boolean[][] visited;
    static int[][][] dp;

    public int solution(int[][] board) {
        map = board;
        visited = new boolean[board.length][board[0].length];

        visited[0][0] = true;
        dp = new int[4][board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                for (int k = 0; k < 4; k++) {
                    dp[k][i][j] = Integer.MAX_VALUE;
                }
            }
        }
        DFS(0,0,0,-1);



        return min;
    }

    public void DFS(int x, int y, int sum, int status) {

        if (x == map.length - 1 && y == map[0].length - 1) {
            min = Math.min(min, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + move[i][0];
            int nextY = y + move[i][1];

            if (range(nextX, nextY) && !wall(nextX, nextY) && !visited[nextX][nextY]) {
                visited[nextX][nextY] = true;
                int newSum = sum + 100;
                if (status != -1 && status != i) {
                    newSum += 500;
                }

                if (dp[i][nextX][nextY] > newSum) {
                    dp[i][nextX][nextY] = newSum;
                    DFS(nextX, nextY, newSum, i);
                }

                visited[nextX][nextY] = false;
            }

        }


    }

    boolean range(int x, int y) {
        return 0 <= x && x < map.length && 0 <= y && y < map[0].length;
    }

    boolean wall(int x, int y) {
        return map[x][y] == 1;
    }

}

public class Main {
    public static void main(String[] args) {
        int[][] board = {{0,0,0}, {0,0,0}, {0,0,0}};
        int result = new Solution().solution(board);
        System.out.println("result = " + result);
    }
}
