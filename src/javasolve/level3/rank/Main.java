package javasolve.level3.rank;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] map = new int[n + 1][n + 1];

        for (int[] result : results) {
            map[result[0]][result[1]] = 1; // 이김
            map[result[1]][result[0]] = -1; // 짐
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == 0) {
                    continue;
                }

                if (map[i][j] == 1) { //i가 이김
                    for (int k = 1; k <= n; k++) {
                        if (map[j][k] == 1) {
                            map[i][k] = 1;
                            map[k][i] = -1;
                        }
                    }
                } else if (map[i][j] == -1) {
                    for (int k = 1; k <= n; k++) {
                        if (map[j][k] == -1) {
                            map[i][k] = -1;
                            map[k][i] = 1;
                        }
                    }
                }

            }
        }
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                if (map[i][j] != 0) {
                    count++;
                }
            }
            if (count == n - 1) {
                answer++;
            }
        }

        return answer;
    }

}


//정확하게 순서를 매길수 잇는 기준?
//2.나를 이긴 사람을 조사, 나를 이긴 사람에게 이긴 사람은 내가 못이김
//3.나에게 진 사람을 조사, 나에게 진 사람에게 진 사람은 내가 이김
//4. 모든 배열이 찬 경우 순서를 알 수 있음

public class Main {
    public static void main(String[] args) {
        int n = 5;
        int[][] result = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        int answer = new Solution().solution(n, result);
        System.out.println("answer = " + answer);
    }
}
