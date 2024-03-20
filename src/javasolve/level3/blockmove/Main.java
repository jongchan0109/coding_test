package javasolve.level3.blockmove;

import java.util.*;

class Solution {

    static class Machine {
        int r1;
        int c1;
        int r2;
        int c2;
        int count;
        boolean status; // true -> 가로, false -> 세로

        public Machine(int r1, int c1, int r2, int c2, int count, boolean status) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.count = count;
            this.status = status;
        }

        @Override
        public String toString() {
            return "Machine{" +
                    "r1=" + r1 +
                    ", c1=" + c1 +
                    ", r2=" + r2 +
                    ", c2=" + c2 +
                    '}';
        }
    }

    static int[][] map;
    static int n;
    static int[][][] dp; // [][][0] -> 가로 , [][][]1 -> 세로

    public int solution(int[][] board) {
        map = board;
        n = board.length;
        dp = new int[n][n][2];

        for (int i = 0; i < n ;i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = Integer.MAX_VALUE;
                dp[i][j][1] = Integer.MAX_VALUE;
            }
        }
        return BFS();
    }

    public int BFS() {
        Queue<Machine> queue = new LinkedList<>();
        int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        queue.add(new Machine(0, 0, 0, 1, 0, true));

        while (!queue.isEmpty()) {

            Machine current = queue.poll();

            if ((current.r1 == n - 1 && current.c1 == n - 1) || (current.r2 == n - 1 && current.c2 == n - 1)) {
                return current.count;
            }

            int status = current.status ? 0: 1;

            if (current.count < dp[current.r1][current.c1][status] || current.count < dp[current.r2][current.c2][status]) {
                dp[current.r1][current.c1][status] = Math.min(dp[current.r1][current.c1][status], current.count);
                dp[current.r2][current.c2][status] = Math.min(dp[current.r2][current.c2][status], current.count);
            } else {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nextR1 = current.r1 + move[d][0];
                int nextC1 = current.c1 + move[d][1];
                int nextR2 = current.r2 + move[d][0];
                int nextC2 = current.c2 + move[d][1];


                if (range(nextR1, nextC1) && range(nextR2, nextC2)) {
                    queue.add(new Machine(nextR1, nextC1, nextR2, nextC2, current.count + 1, current.status));
                }
            }

            if (current.status) { // 가로인 경우
                int left = Math.min(current.c1, current.c2);
                int right = Math.max(current.c1, current.c2);

                //왼쪽을 기준으로 시계방향
                if (range(current.r1 + 1, left) && range(current.r1 + 1, right)) {
                    queue.add(new Machine(current.r1, left, current.r1 + 1, left, current.count + 1, false));
                }

                //왼쪽을 기준으로 반시계방향
                if (range(current.r1 - 1, left) && range(current.r1 - 1, right)) {
                    queue.add(new Machine(current.r1 - 1, left, current.r1, left, current.count + 1, false));
                }


                //오른쪽을 기준으로 시계방향
                if (range(current.r1 - 1, right) && range(current.r1 - 1, left)) {
                    queue.add(new Machine(current.r1 - 1, right, current.r1, right, current.count + 1, false));
                }

                //오른쪽을 기준으로 반시계방향
                if (range(current.r1 + 1, right) && range(current.r1 + 1, left)) {
                    queue.add(new Machine(current.r1, right, current.r1 + 1, right, current.count + 1, false));
                }


            } else {
                int bottom = Math.min(current.r1, current.r2);
                int top = Math.max(current.r1, current.r2);

                //아래를 기준으로 반시계방향
                if (range(bottom, current.c1 + 1) && range(top, current.c1 + 1)) {
                    queue.add(new Machine(bottom, current.c1, bottom, current.c1 + 1, current.count + 1, true));
                }

                //아래를 기준으로 시계방향
                if (range(bottom, current.c1 - 1) && range(top, current.c1 - 1)) {
                    queue.add(new Machine(bottom, current.c1 - 1, bottom, current.c1, current.count + 1, true));
                }

                //위를 기준으로 시계방향
                if (range(top, current.c1 - 1) && range(bottom, current.c1 - 1)) {
                    queue.add(new Machine(top, current.c1 - 1, top, current.c1, current.count + 1, true));
                }

                //위를 기준으로 반시계방향
                if (range(top, current.c1 + 1) && range(bottom, current.c1 + 1)) {
                    queue.add(new Machine(top, current.c1, top, current.c1 + 1, current.count + 1, true));
                }
            }

        }
        return 0;
    }

    public boolean range(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n && map[r][c] != 1;
    }


}


public class Main {
    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}};
        int result = new Solution().solution(board);
        System.out.println("result = " + result);
    }
}
