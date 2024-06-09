package boj.gold.b1806;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long s = scanner.nextLong();
        long sum = 0L;
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
            sum += arr[i];
        }

        if (sum < s) {
            System.out.println(0);
            return;
        }

        int start = 0;
        int end = 0;
        sum = 0L;
        int min = Integer.MAX_VALUE;

        while (start < n && end <= n) {

            if (sum < s && end < n) {
                sum += arr[end++];
            } else {
                sum -= arr[start++];
            }

            if (sum >= s) {
                min = Math.min(min, end - start);
            }

        }

        System.out.println(min);
    }

}
