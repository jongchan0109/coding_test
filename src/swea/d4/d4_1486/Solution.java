package swea.d4.d4_1486;


import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();


        for(int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int b = sc.nextInt();
            int s = 0;
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
                s += arr[i];
            }
            boolean[] check;
            check = new boolean[s+1];
            check[0] = !check[0];

            for (int i = 0; i < n; i++) {
                for (int j = s; j >= arr[i]; j--) {
                    if (check[j - arr[i]]) {
                        check[j] = true;
                    }
                }
            }

            for (int i = b; i <= s; i++) {
                if (check[i]) {
                    System.out.println("#" + test_case + " " + (i - b));
                    break;
                }
            }

        }
    }
}
