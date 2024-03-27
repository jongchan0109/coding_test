package javasolve.level3.airplane;

class Solution {

    public long solution(int n, int[] times) {
        long right = 1000000000L * 1000000000;
        long left = 1;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (check(n, times, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }
        return left;
    }

    private boolean check(int n , int[] times, long mid) {
        long sum = 0L;

        for (int time: times) {
            sum += mid / time;
        }
        return sum >= n;
    }

}

public class Main {
    public static void main(String[] args) {
        int n = 6;
        int[] times = {7,10};
        long result = new Solution().solution(n,times);
        System.out.println("result = " + result);
    }
}
