package swea.d4.d4_3752;

import java.util.*;

class Solution {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            int sum = 0;

            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
                sum += arr[i];
            }
            boolean[] check = new boolean[sum + 1];
            check[0] = true;

            for (int i = 0; i < n; i++) {
                for (int j = sum; j >= arr[i]; j--) {
                    if (check[j-arr[i]]) {
                        check[j] = true;
                    }
                }
            }

            int count = 0;

            for (boolean possible: check) {
                if (possible) {
                    count++;
                }
            }
            System.out.println("#" + test_case + " " + count);
        }
    }


}
