package javasolve.level3.fcfsscheduling;

class Solution {

    public int solution(int n, int[] cores) {

        int left = 0;
        int right = 100000000;

        while (left <= right) {
            int mid = (left + right) / 2;
            int sum = calculate(mid, cores);

            if (sum >= n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        int time = left;
        int count = calculate(time - 1, cores);

        for (int i = 0; i < cores.length; i++) {
            if (time % cores[i] == 0) {
                count++;
                if (count == n) {
                    return i + 1;
                }
            }
        }
        return 0;

    }

    private int calculate(int time, int[] cores) {
        int sum = 0;
        for (int core : cores) {
            sum += time / core + 1;
        }
        return sum;
    }
}


public class Main {
    public static void main(String[] args) {
        int n = 6;
        int[] cores = {1, 2, 3};
        int result = new Solution().solution(n, cores);
        System.out.println("result = " + result);
    }
}
