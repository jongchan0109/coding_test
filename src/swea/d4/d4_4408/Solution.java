package swea.d4.d4_4408;

import java.util.Scanner;

public class Solution{
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            int[] dp = new int[401];

            int n = sc.nextInt();

            for (int i = 0; i < n; i++) {

                int from = sc.nextInt();
                int to = sc.nextInt();

                if (from > to) {
                    int temp = from;
                    from = to;
                    to = temp;
                }

                if (to % 2 == 1) {
                    to +=1;
                }

                for (int j = from; j <= to; j++) {
                    dp[j]++;
                }

            }
            int result = 0;
            for (int i = 1; i <= 400; i++) {
                result = Math.max(result, dp[i]);
            }
            System.out.println("#" + test_case + " " + result);

        }
    }
}
