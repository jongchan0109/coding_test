package boj.gold.b2632;

import java.util.Scanner;

public class Main {

    static int size;
    static int n;
    static int m;
    static int[] a;
    static int[] b;


    static int[] countA;
    static int[] countB;



    public static void main(String[] args) {
        input();
        int result = solve();
        System.out.println(result);
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        size = scanner.nextInt();
        n = scanner.nextInt();
        m = scanner.nextInt();

        int sumA = 0;
        int sumB = 0;

        a = new int[n];
        b = new int[m];

        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            sumA += a[i];
        }

        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
            sumB += b[i];
        }

        countA = new int[Math.max(sumA, size) + 1];
        countB = new int[Math.max(sumB, size) + 1];

        countA[sumA] = 1;
        countB[sumB] = 1;

    }

    private static int solve() {

        count(a,countA);
        count(b,countB);

        int sum = 0;

        for (int i = 0; i <= size; i++) {
            sum +=  countA[i] * countB[size-i];
        }
        return sum;

    }

    private static void count(int[] pizza, int[] count) {
        count[0] = 1;

        for (int i = 0; i < pizza.length; i++) {
            int sum = 0;
            for (int j = 1; j < pizza.length; j++) { // 선택하는 피자의 개수
                sum += pizza[(i + j ) % pizza.length];
                if (sum > size) {
                    break;
                }
                count[sum]++;
            }
        }
    }

}
