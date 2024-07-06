package boj.gold.b1022;

import java.util.Scanner;

public class Main {

    static int r1;
    static int c1;
    static int r2;
    static int c2;
    static int[][] board;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        input();
        board = new int[r2 - r1 + 1][c2 - c1 + 1];
        int max = 0;

        fill();

        for (int i = 0; i <= r2 - r1; i++) {
            for (int j = 0; j <= c2 - c1; j++) {
                max = Math.max(max, board[i][j]);
            }
        }

        int len = String.valueOf(max).length();

        for (int i = 0; i <= r2 - r1; i++) {
            for (int j = 0; j <= c2 - c1; j++) {
                System.out.printf("%" + len + "d ", board[i][j]);
            }
            System.out.println();
        }

    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        r1 = scanner.nextInt();
        c1 = scanner.nextInt();
        r2 = scanner.nextInt();
        c2 = scanner.nextInt();
    }

    private static void fill() {

        int x = 0;
        int y = 0;
        int dir = 0;

        int cnt = 0;
        int dNum = 1;
        int num = 1;


        while (!isEnd()) {
            if (x >= r1 && x <= r2 && y >= c1 && y <= c2) {
                board[x - r1][y - c1] = num;
            }
            num++;
            cnt++;
            x = x + dx[dir];
            y = y + dy[dir];

            if (cnt == dNum) {
                cnt = 0;
                if (dir == 1 || dir == 3) {
                    dNum++;
                }
                dir = (dir + 1) % 4;
            }
        }
    }

    private static boolean isEnd() {
        return board[0][0] != 0 && board[r2 - r1][0] != 0 && board[r2 - r1][c2 - c1] != 0 && board[0][c2 - c1] != 0;
    }
}
