package programmers.javasolve.level3.sticker;

class Solution {
    public int solution(int[] sticker) {
        int length = sticker.length;
        if (length == 1) return sticker[0];
        if (length == 2) return Math.max(sticker[0], sticker[1]);
        int[] tmp_sticker = new int[length];
        int[] tmp_sticker2 = new int[length];
        System.arraycopy(sticker, 0, tmp_sticker, 0, length);
        tmp_sticker[length - 1] = 0;

        System.arraycopy(sticker, 1, tmp_sticker2, 0, length - 1);
        tmp_sticker2[length - 1] = 0;


        // fix circle -> 1 dimension
        // start 1st elem
        int[] dp = new int[length];
        int[] dp2 = new int[length];


        //init
        max(length, tmp_sticker, dp);
        max(length, tmp_sticker2, dp2);


        return Math.max(dp[length - 1], dp2[length - 1]);
    }

    private void max(int length, int[] tmp_sticker, int[] dp) {
        dp[0] = tmp_sticker[0];
        dp[1] = Math.max(dp[0], tmp_sticker[1]);


        for (int i = 0; i < length - 2; i++) {
            dp[i + 2] = Math.max(dp[i] + tmp_sticker[i + 2], dp[i + 1]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int[] sticker = {14, 6, 5, 11, 3, 9, 2, 10};
        int result = new Solution().solution(sticker);
        System.out.println("result = " + result);
    }
}
