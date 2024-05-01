package programmers.javasolve.level3.change;

import java.util.*;

class Solution {

    static final int DIV = 1000000007;

    public int solution(int n, int[] money) {
        int[] dp = new int[n + 1];
        Arrays.sort(money);

        for (int i = 0; i < n + 1; i++) {
            if (i % money[0] == 0) {
                dp[i]++;
            }
        }

        for (int i = 1; i < money.length; i++) {
            for (int j = money[i]; j <= n; j++) {
                dp[j] += dp[j - money[i]];
                dp[j] %= DIV;
            }
        }


        return dp[n];
    }
}


public class Main {
    public static void main(String[] args) {
        int n = 5;
        int[] money = {1, 2, 5};
        int result = new Solution().solution(n, money);
        System.out.println("result = " + result);
    }
}
