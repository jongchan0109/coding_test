package swea.d1.d1_2063;

import java.util.Arrays;
import java.util.Scanner;

class Solution {

    static int n;
    static int[] arr;

    public static void main(String[] args) {
        input();
        System.out.println(middle());
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
    }

    private static int middle() {
        Arrays.sort(arr);
        return arr[(n - 1) / 2];
    }
}
