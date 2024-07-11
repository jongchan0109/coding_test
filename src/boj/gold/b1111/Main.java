package boj.gold.b1111;

import java.util.Scanner;

public class Main {

    static int n;
    static int[] arr;

    public static void main(String[] args) {
        input();
        solve();

    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
    }

    private static void solve() {

        if (n == 1) {
            System.out.println("A");
            return;
        } else if (n == 2) {
            if (arr[0] == arr[1]) {
                System.out.println(arr[0]);
            } else {
                System.out.println("A");
            }
            return;
        }
        int a;
        int b;

        if (arr[1] == arr[0]) {
            a = 1;
            b = 0;
        } else {

            if ((arr[2] - arr[1]) % (arr[1] - arr[0]) != 0) {
                System.out.println("B");
                return;
            }
            a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
            b = arr[1] - a * arr[0];
        }


        for (int i = 1; i < n; i++) {
            if (arr[i] != a * arr[i - 1] + b) {
                System.out.println("B");
                return;
            }
        }
        System.out.println(a * arr[n - 1] + b);
    }
}
