package swea.d3.d3_20019;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int testCase = 1; testCase<= T ; testCase++) {
            int[] arr = new int[3];
            boolean flag = false;
            int result = 0;

            for (int i = 0; i < 3; i++) {
                arr[i] = sc.nextInt();
            }

            for (int i = 2; i > 0 ; i--) {
                if (arr[i] > arr[i-1]) {
                    continue;
                }
                result += arr[i-1] - arr[i] + 1;
                arr[i-1] = arr[i] - 1;
                if (arr[i-1] < 1) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                System.out.println(-1);
            } else {
                System.out.println("result = " + result);
            }
        }
    }
}
