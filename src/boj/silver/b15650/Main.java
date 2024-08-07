package boj.silver.b15650;

import java.util.Scanner;

public class Main {

    static int n;
    static int m;
    static StringBuilder sb;
    static int[] arr;

    public static void main(String[] args) {
        init();

        dfs(1, 0);
        System.out.println(sb);
    }

    private static void init() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        arr = new int[m];
        sb = new StringBuilder();
    }

    private static void dfs(int start, int depth) {
        if (depth == m) {
            for (int i : arr) {
                sb.append(i);
                sb.append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i <= n; i++) {
            arr[depth] = i;
            dfs(i + 1, depth + 1);
        }

    }
}
