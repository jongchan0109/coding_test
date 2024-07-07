package boj.gold.b1256;

import java.util.Scanner;

public class Main {

    static long[][] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        dp = new long[n + m + 1][n + m + 1];

        // 조합 값을 미리 계산해둠
        combination(n, m);

        if (k > dp[n + m][m]) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();

        while (n > 0 && m > 0) {
            long comb = dp[n + m - 1][m]; // 조합 값을 계산
            if (comb >= k) { // k번째 문자열이 현재 'a'를 선택했을 때 가능한 조합 수 범위 내에 있는지 확인
                sb.append('a');
                n--;
            } else {
                sb.append('z');
                k -= (int) comb; // 'z'를 선택했으므로 k에서 comb 값을 뺍니다.
                m--;
            }
        }

        // 남아있는 'a'나 'z'를 결과 문자열에 추가
        while (n > 0) {
            sb.append('a');
            n--;
        }
        while (m > 0) {
            sb.append('z');
            m--;
        }

        System.out.println(sb);
    }

    private static void combination(int n, int m) {
        for (int i = 0; i <= n + m; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for (int i = 1; i <= n + m; i++) {
            for (int j = 1; j < i; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                if (dp[i][j] > 1_000_000_000) {
                    dp[i][j] = 1_000_000_001; // overflow 처리
                }
            }
        }
    }
}
