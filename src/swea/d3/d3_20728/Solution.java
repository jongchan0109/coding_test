package swea.d3.d3_20728;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int K = sc.nextInt();
            int[] arr = new int[N];

            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }

            Arrays.sort(arr);

            int start = arr[0];
            int end = arr[K - 1];
            int result = end - start;

            for (int i = 1; i <= N - K; i++) {
                start = arr[i];
                end = arr[i + K - 1];
                result = Math.min(result, end - start);
            }

            System.out.println("result = " + result);
        }
    }

}
