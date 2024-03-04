package javasolve.level3.silvergold;

class Solution {

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {

        long left = 0L;
        long right = 200000L * 2000000000L;

        while (left <= right) {
            long time = (left + right) / 2;

            if (solve(a,b,g,s,w,t,time)) {
                right = time -1;
            } else {
                left = time + 1;
            }

        }
        return left;
    }

    public boolean solve(int a, int b, int[] g, int[] s, int[] w, int[] t, long time) {
        int n = g.length;
        long totalGold = 0L;
        long totalSilver = 0L;
        long total = 0L;

        for (int i = 0; i < n; i++) {
            long move = time / t[i];
            if (move % 2 == 1) {
                move++;
            }
            move/=2;

            long tmp = Math.min(move * w[i], g[i] + s[i]);
            total += tmp;
            totalGold += Math.min(tmp,g[i]);
            totalSilver += Math.min(tmp,s[i]);
        }

        return total >= a + b && totalGold >= a && totalSilver >= b;

    }
}

public class Main {
    public static void main(String[] args) {
        int a = 10;
        int b = 10;
        int[] g = {100};
        int[] s = {100};
        int[] w = {7};
        int[] t = {10};

        long result = new Solution().solution(a,b,g,s,w,t);
        System.out.println("result = " + result);
    }
}
