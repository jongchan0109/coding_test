package boj.gold.b22866;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    static int N;
    static int[] arr;
    static Stack<Integer> stack;
    static int[][] result;

    public static void main(String[] args) {
        input();
        solve();

        for (int i = 1; i <= N; i++) {
            System.out.print(result[i][0]);
            if (result[i][0] == 0) {
                System.out.println();
            } else {
                System.out.println(" " + result[i][1]);
            }
        }
    }

    private static void solve() {

        stack = new Stack<>();

        //left
        for (int i = 1; i <= N; i++) {
            while(!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            result[i][0] = stack.size();

            if (result[i][0] > 0) {
                result[i][1] = stack.peek();
            }
            stack.push(i);
        }


        stack = new Stack<>();

        for (int i = N; i > 0; i--) {
            while(!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            result[i][0] += stack.size();
            if (!stack.isEmpty() && stack.peek() - i < i - result[i][1]) {
                result[i][1] = stack.peek();
            }
            stack.push(i);
        }

    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        result = new int[N + 1][2];
        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = scanner.nextInt();
            result[i][1] = -100_000;
        }

    }

}
