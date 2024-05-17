package swea.d4.d4_3459;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            long n = sc.nextLong();

            while (n > 10) {
                n = (n / 2) + 1;
                n = (n / 2) - 1;
            }

            if (n == 1 || 6 <= n && n <= 9) {
                System.out.println("#" + test_case + " Bob");
            } else {
                System.out.println("#" + test_case + " Alice");
            }

        }
    }
}
