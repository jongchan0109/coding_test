package swea.d1.d1_2058;

import java.util.Scanner;

class Solution {

    static int num;

    public static void main(String[] args) {
        input();
        System.out.println(calculate());
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        num = scanner.nextInt();
    }

    private static int calculate() {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
