package programmers.javasolve.level3.numbercontest;

import java.util.Arrays;

class Solution {

    public static int INF = 10000;
    public static int[][] move = new int[10][10];
    public static String str;
    public static int[][][] dp;

    public int solution(String numbers) {
        str = numbers;
        calculate();
        dp = new int[10][10][numbers.length()];

        return dynamic(4, 6, 0);

    }


    int dynamic( int left, int right, int index) {

        if (index >= str.length()) {
            return 0;
        }
        if (dp[left][right][index] != 0) {
            return dp[left][right][index];
        }

        int num = str.charAt(index) - '0';
        int result = Integer.MAX_VALUE;


        if (num != right) {
            result = Math.min(dynamic(num, right, index + 1) + move[left][num], result);
        }
        if (num != left) {
            result = Math.min(dynamic(left, num, index + 1) + move[right][num], result);

        }
        dp[left][right][index] = result;

        return dp[left][right][index];
    }

    void calculate() {

        for (int i = 0; i < 10; i++) {
            Arrays.fill(move[i], INF);
        }

        for (int i = 0; i < 10; i++) {
            move[i][i] = 1;
        }
        // 위 아래
        for (int i = 1; i <= 6; i++) {
            move[i][i + 3] = 2;
            move[i + 3][i] = 2;
        }

        // 좌우
        for (int i = 1; i <= 2; i++) {
            move[i][i + 1] = 2;
            move[i + 1][i] = 2;
            move[i + 3][i + 4] = 2;
            move[i + 4][i + 3] = 2;
            move[i + 6][i + 7] = 2;
            move[i + 7][i + 6] = 2;
        }

        // 대각선

        for (int i = 1; i <= 2; i++) {
            move[i][i + 4] = 3;
            move[i + 4][i] = 3;
            move[i + 3][i + 7] = 3;
            move[i + 7][i + 3] = 3;
        }

        for (int i = 2; i <= 3; i++) {
            move[i][i + 2] = 3;
            move[i + 2][i] = 3;
            move[i + 3][i + 5] = 3;
            move[i + 5][i + 3] = 3;
        }

        move[9][0] = 3;
        move[0][9] = 3;
        move[7][0] = 3;
        move[0][7] = 3;
        move[0][8] = 2;
        move[8][0] = 2;

        for (int i = 0; i < 10; i++) {
            dijkstra(i);
        }

    }

    void dijkstra(int from) {
        boolean[] visit = new boolean[10];
        int[] dist = new int[10];

        // 거리 배열 초기화
        System.arraycopy(move[from], 0, dist, 0, 10);

        visit[from] = true;

        // 모든 정점을 방문할 때까지 반복
        while (true) {
            // 방문하지 않은 정점 중 최소 거리를 가진 정점 선택
            int minDist = INF;
            int minIdx = -1;
            for (int i = 0; i < 10; i++) {
                if (!visit[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    minIdx = i;
                }
            }

            // 방문하지 않은 정점이 없으면 종료
            if (minIdx == -1) break;

            // 선택된 정점 방문 처리
            visit[minIdx] = true;

            // 선택된 정점으로부터 인접한 정점들의 거리 업데이트
            for (int i = 0; i < 10; i++) {
                if (!visit[i] && move[minIdx][i] != INF) {
                    dist[i] = Math.min(dist[i], dist[minIdx] + move[minIdx][i]);
                }
            }
        }

        System.arraycopy(dist, 0, move[from], 0, 10);
    }
}


public class Main {
    public static void main(String[] args) {
        String numbers = "1234";
        int result = new Solution().solution(numbers);
        System.out.println("result = " + result);
    }
}