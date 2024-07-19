package boj.gold.b1208;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int n;
    static int s;
    static int[] arr;


    public static void main(String[] args) {
        input();

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        makeList(0, 0, n / 2, left);
        makeList(0, n / 2, n, right);

        Collections.sort(left);
        Collections.sort(right);


        long result = solve(left, right);

        result = s == 0 ? result - 1 : result;

        System.out.println(result);
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        s = scanner.nextInt();

        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }


    }

    private static void makeList(int sum, int start, int end, List<Integer> list) {
        if (start == end) {
            list.add(sum);
            return;
        }
        makeList(sum, start + 1, end, list);
        makeList(sum + arr[start], start + 1, end, list);
    }

    private static long solve(List<Integer> left, List<Integer> right) {
        int l = 0;
        int r = right.size() - 1;
        long count = 0L;

        while (l < left.size() && r >= 0) {
            if (left.get(l) + right.get(r) == s) {
                long leftCount = 0;
                long rightCount = 0;

                int rValue = right.get(r);
                int lValue = left.get(l);

                while (r >= 0 && right.get(r) == rValue) {
                    rightCount++;
                    r--;
                }

                while (l < left.size() && left.get(l) == lValue) {
                    leftCount++;
                    l++;
                }
                count += rightCount * leftCount;
            } else if (left.get(l) + right.get(r) > s) {
                r--;
            } else {
                l++;
            }

        }
        return count;
    }
}

