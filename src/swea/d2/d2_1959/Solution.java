package swea.d2.d2_1959;

import java.util.Scanner;

class Solution {

    static int n;
    static int m;
    static int[] a;
    static int[] b;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            n = sc.nextInt();
            m = sc.nextInt();

            a = new int[n];
            b = new int[m];

            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            for (int i = 0; i < m; i++) {
                b[i] = sc.nextInt();
            }
            System.out.println("#" + test_case + " " + solve());
        }
    }

    private static int solve() {
        int max = Integer.MIN_VALUE;

        if (n > m) {
            for (int i = 0; i <= n - m; i++) {
                int sum = 0;
                for (int j = 0; j < m; j++) {
                    sum += a[i + j] * b[j];
                }
                max = Math.max(max, sum);
            }

        } else {
            for (int i = 0; i <= m - n; i++) {
                int sum = 0;
                for (int j = 0; j < n; j++) {
                    sum += a[j] * b[i + j];
                }
                max = Math.max(max, sum);
            }
        }
        return max;
    }
}