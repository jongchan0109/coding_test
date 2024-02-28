package javasolve.level3.game;

import java.util.*;

class Solution {

    static int row;
    static int col;
    static final int INF = 100;

    static int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int DFS(int ax, int ay, int bx, int by, boolean turn, int[][] board) {

        int x;
        int y;
        if (turn) {
            x = ax;
            y = ay;
        } else {
            x = bx;
            y = by;
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int nextX = x + move[i][0];
            int nextY = y + move[i][1];

            if (!canMove(nextX, nextY, board)) {
                continue;
            }

            if (ax == bx && ay == by) {
                list.add(1);
                break;
            }

            board[x][y] = 0;
            int result;

            if (turn) {
                result = -DFS(nextX, nextY, bx, by, false, board);
            } else {
                result = -DFS(ax, ay, nextX, nextY, true, board);
            }
            board[x][y] = 1;

            if (result >= 0) {
                result++;
            } else {
                result--;
            }
            list.add(result);

        }

        int win = INF;
        int lose = INF;

        // 패배한 분기만 있으면, 패배한 것들 중 가장 큰 것을 리턴
        // 승리한 것이 있으면, 승리한 것들 중 가장 짧은 것을 리턴

        // 움직일 수 없는 경우
        if (list.isEmpty()) {
            return 0;
        }

        for (int num : list) {
            if (num > 0) {
                win = Math.min(win, num);
            } else {
                lose = Math.min(lose, num);
            }
        }
        if (win == INF) { // 이긴 경우가 없는 경우
            return lose;
        } else {
            return win;
        }


    }


    public int solution(int[][] board, int[] aloc, int[] bloc) {

        row = board.length;
        col = board[0].length;

        return Math.abs(DFS(aloc[0], aloc[1], bloc[0], bloc[1], true, board));
    }

    public boolean canMove(int x, int y, int[][] board) {

        if (x < 0 || x >= row || y < 0 || y >= col) {
            return false;
        }
        return board[x][y] != 0;
    }


}

public class Main {
    public static void main(String[] args) {
        int[][] board = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[] aLoc = {1, 0};
        int[] bLoc = {1, 2};
        int result = new Solution().solution(board, aLoc, bLoc);
        System.out.println("result = " + result);
    }
}
