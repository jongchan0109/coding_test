package boj.gold.b2166;

import java.util.Scanner;

public class Main {

    static int n;

    static int[] x;
    static int[] y;

    public static void main(String[] args) {
        input();

        System.out.printf("%.1f", calculate());

    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        x = new int[n];
        y = new int[n];

        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        scanner.close();
    }

    //신발끈 공식
    private static double calculate() {
        double sum = 0;

        for (int i = 0; i < n; i++) {
            sum += (double)x[i] * y[(i+1) % n];
            sum -= (double)y[i] * x[(i+1) % n];
        }
        return Math.abs(sum) / 2;
    }

}

