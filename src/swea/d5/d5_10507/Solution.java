package swea.d5.d5_10507;

import java.util.Scanner;

public class Solution {

    static int n;
    static int p;
    static int[] days;
    static int maxPeriod;
    static int[] dif;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int testCase = scanner.nextInt();

        for (int t = 1; t <= testCase; t++) {

            maxPeriod = 0;
            n = scanner.nextInt();
            p = scanner.nextInt();
            days = new int[n];
            dif = new int[n]; //dif[0] = 0 ~ 1, dif[1] = 1 ~ 2

            for (int i = 0; i < n; i++) {
                days[i] = scanner.nextInt();
            }

            int s = 0;

            dif[0] = 0;
            for (int i = 0; i < n - 1; i++) {
                s += days[i+1] - days[i] - 1;
                dif[i + 1] = s;
            }

            for (int i = 0; i < n; i++) {
                binarySearch(i);
            }

            System.out.println("#" + t + " " + maxPeriod);
        }

    }

    private static void binarySearch(int start) {
        int left = start;
        int right = n - 1;


        while (left <= right) {

            int mid = (left + right) / 2;

            int notStudy = dif[mid] - dif[start];

            int remainP = p - notStudy;



            if (notStudy > p) {
                right = mid - 1;
            } else {
                maxPeriod = Math.max(days[mid] - days[start] + 1 + remainP, maxPeriod);

                left = mid + 1;
            }


        }
    }

}

