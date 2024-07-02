package swea.d2.d2_12712;

import java.util.Scanner;

class Solution {

    static int N;
    static int M;
    static int[][] board;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            input(sc);
            int result = searchMaxSum();
            System.out.println("#" + t + " " + result);
        }

    }

    private static void input(Scanner sc) {
        N = sc.nextInt();
        M = sc.nextInt();
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = sc.nextInt();
            }
        }
    }

    private static int searchMaxSum() {
        int max = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, spray(i, j));
            }
        }
        return max;
    }

    private static int spray(int row, int col) {

        int sum1 = 0;
        int sum2 = board[row][col];

        for (int i = Math.max(0, row - M + 1); i <= Math.min(N - 1, row + M -1); i++) {
            sum1 += board[i][col];
        }
        for (int i = Math.max(0, col - M + 1); i <= Math.min(N - 1, col + M -1); i++) {
            sum1 += board[row][i];
        }
        sum1 -= board[row][col];

        int len = 1;
        while (len < M) {

            if (row - len >= 0) {
                if (col - len >= 0) {
                    sum2 += board[row - len][col - len];
                }
                if (col + len < N) {
                    sum2 += board[row - len][col + len];
                }
            }

            if (row + len < N) {
                if (col - len >= 0) {
                    sum2 += board[row + len][col - len];
                }
                if (col + len < N) {
                    sum2 += board[row + len][col + len];
                }
            }

            len++;
        }

        return Math.max(sum1, sum2);
    }
}
