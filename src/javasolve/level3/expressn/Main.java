package javasolve.level3.expressn;

class Solution {
    public int solution(int N, int number) {
        int answer = dfs(N, number, 0, 0);
        return answer > 8 ? -1 : answer;
    }

    private int dfs(int n, int number, int count, int current) {
        if (count > 8) { // 주어진 조건에 따라 최솟값이 8보다 크면 -1을 반환
            return -1;
        }
        if (current == number) { // 현재 숫자가 목표 숫자와 같으면 반환
            return count;
        }

        // 다음 경우의 수를 탐색하며 최솟값을 구함
        int nn = n;
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 8 - count; i++) {
            int newCount = count + i + 1;
            int next = dfs(n, number, newCount, current + nn);
            if (next > 0) { // 유효한 값이면 최솟값 갱신
                result = Math.min(result, next);
            }
            next = dfs(n, number, newCount, current - nn);
            if (next > 0) {
                result = Math.min(result, next);
            }
            next = dfs(n, number, newCount, current * nn);
            if (next > 0) {
                result = Math.min(result, next);
            }
            next = dfs(n, number, newCount, current / nn);
            if (next > 0) {
                result = Math.min(result, next);
            }

            nn = nn * 10 + n; // N을 이어붙여 새로운 수를 만듦
        }

        return result == Integer.MAX_VALUE ? -1 : result; // 결과가 없으면 -1 반환
    }
}

public class Main {
    public static void main(String[] args) {
        int N = 5;
        int number = 12;
        int result = new Solution().solution(N,number);
        System.out.println("result = " + result);
    }
}
