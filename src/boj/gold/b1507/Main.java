package boj.gold.b1507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n;
    static int[][] board;
    static int[][] arr;
    static final int INF = 2500;

    public static void main(String[] args) throws IOException {
        input();
        int result = solve();
        System.out.println(result);
    }

    private static void input() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        board = new int[n][n];
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] strings = bf.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = board[i][j] = Integer.parseInt(strings[j]);
            }
        }
    }

    private static int solve() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (i == j || i == k || k == j) {
                        continue;
                    }
                    if (board[i][j] > board[i][k] + board[k][j]) {
                        return -1;
                    }
                    if (board[i][j] == board[i][k] + board[k][j]) {
                        arr[i][j] = INF;
                    }
                }
            }
        }

        int sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (arr[i][j] != INF) {
                    sum += board[i][j];
                }
            }
        }

        return sum;
    }
}
