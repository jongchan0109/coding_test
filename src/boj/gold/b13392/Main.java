package boj.gold.b13392;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[][] dp;
    static int n;
    static int[] from;
    static int[] to;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();
        String temp1 = scanner.next();
        String temp2 = scanner.next();

        from = new int[n + 1];
        to = new int[n + 1];
        dp = new int[n+1][10];

        for (int i = 1; i <= n; i++) {
            from[i] = temp1.charAt(i - 1) - '0';
            to[i] = temp2.charAt(i - 1) - '0';
        }
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(solve(1,0));

    }

    public static int solve(int index, int turn) {
        if (index == n + 1) {
            return 0;
        }
        if (dp[index][turn] != -1) {
            return dp[index][turn];
        }


        int turnLeft = (to[index] - from[index] - turn + 20) % 10;
        int turnRight = 10 - turnLeft;

        dp[index][turn] = Math.min(solve(index + 1, turn) + turnRight, solve(index + 1, (turn + turnLeft) % 10) + turnLeft);

        return dp[index][turn];


    }

}

