package boj.gold.b1365;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int n;
    static int[] arr;
    static List<Integer> lis;

    public static void main(String[] args) {
        input();
        int result = lisBinarySearch();

        System.out.println(result);
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        lis = new ArrayList<>();

    }

    private static int binarySearch(int right, int element) {
        int left = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (element > lis.get(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }


    private static int lisBinarySearch() {
        lis.add(arr[0]);

        for (int i = 1; i < n; i++) {
            if (arr[i] > lis.get(lis.size() - 1)) {
                lis.add(arr[i]);
            } else {
                int index = binarySearch(lis.size() - 1, arr[i]);
                lis.set(index, arr[i]);
            }
        }

        return n - lis.size();
    }

}
