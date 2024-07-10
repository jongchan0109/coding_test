package boj.gold.b1082;

import java.util.Scanner;

public class Main {

    static int n;
    static int m;
    static int[] p;
    static int[] numbers;
    static int start;

    public static void main(String[] args) {
        input();
        solve();
        print();
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = scanner.nextInt();
        }
        m = scanner.nextInt();
    }

    private static void solve() {
        int min = 51;
        int minIdx = -1;

        for (int i = 0; i < n; i++) {
            if (p[i] <= min) {
                min = p[i];
                minIdx = i;
            }
        }
        numbers = new int[m / min];

        for (int i = 0; i < m / min; i++) {
            numbers[i] = minIdx;
        }

        int totalMoney = m % min;
        start = 0;

        for (int i = 0; i < m / min; i++) {
            for (int j = n - 1; j > minIdx; j--) {
                if (p[j] <= totalMoney + min) {
                    totalMoney = totalMoney + min - p[j];
                    numbers[i] = j;
                    break;
                }

            }
            if (numbers[start] == 0) {
                start++;
                totalMoney += min;
            }
        }
    }

    private static void print() {

        if (start == numbers.length) {
            System.out.println(0);
        } else {
            boolean flag = true;
            for (int number : numbers) {
                if (number != 0) {
                    flag = false;
                    System.out.print(number);
                } else {
                    if (!flag) {
                        System.out.print(number);
                    }
                }
            }

        }
    }
}
