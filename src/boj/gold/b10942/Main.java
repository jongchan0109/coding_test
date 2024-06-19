package boj.gold.b10942;

import java.util.Scanner;

public class Main {

    static int n;
    static int[] arr;
    static int k;
    static boolean[][] dp;
    static Scanner scanner;

    public static void main(String[] args) {
        input();
        dynamicPrograming();
        print();
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            int start = scanner.nextInt() - 1;
            int end = scanner.nextInt() - 1;

            if (dp[start][end]) {
                sb.append("1\n");
            } else {
                sb.append("0\n");
            }
        }
        System.out.println(sb);
    }

    private static void input() {
        scanner = new Scanner(System.in);
        n = scanner.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        k = scanner.nextInt();
    }

    private static void dynamicPrograming() {
        dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        for (int i = 0; i < n-1; i++) {
            if (arr[i] == arr[i+1]) {
                dp[i][i+1] = true;
            }
        }

        for (int length = 2; length <= n; length++) {
            for (int s = 0; s + length < n; s++) {
                int e = s + length;
                if (dp[s+1][e-1] && arr[s] == arr[e]) {
                    dp[s][e] = true;
                }
            }
        }
    }
}

