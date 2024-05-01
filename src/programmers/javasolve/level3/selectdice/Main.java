package programmers.javasolve.level3.selectdice;

import java.util.*;

class Solution {

    static List<int[]> selectA = new ArrayList<>();
    static boolean[] visited;
    static int n;

    public int[] solution(int[][] dice) {
        n = dice.length;
        visited = new boolean[n];

        combination(0, 0, new int[n / 2]);
        int max = 0;
        int[] answer = new int[n/2];

        for (int[] a: selectA) {

            int[] b = new int[n / 2];
            int index = 0;
            boolean[] selectB = new boolean[n];

            for (int elementA: a) {
                selectB[elementA] = true;
            }

            for (int i = 0; i < n ; i++) {
                if (!selectB[i]) {
                    b[index++] = i;
                }
            }

            List<Integer> scoreA = new ArrayList<>();
            List<Integer> scoreB = new ArrayList<>();

            calculate(0, 0, a, dice, scoreA);
            calculate(0, 0, b, dice, scoreB);

            Collections.sort(scoreA);
            Collections.sort(scoreB);

            int totalScore = 0;

            for (int num: scoreA) {
                int left = 0;
                int right = scoreA.size();

                while (left + 1 < right) {
                    int mid = (left + right) / 2;
                    if (num > scoreB.get(mid)) {
                        left = mid;
                    } else {
                        right = mid;
                    }
                }
                totalScore += left;
            }

            if (totalScore > max) {
                max = totalScore;
                answer = a;
            }

        }

        for (int i = 0; i < answer.length ; i++) {
            answer[i] += 1;
        }

        return answer;
    }

    public void calculate(int depth, int sum, int[] select, int[][] dice, List<Integer> score) {
        if (depth == n / 2) {
            score.add(sum);
            return;
        }
        for (int i = 0; i < 6; i++) {
            calculate(depth + 1, sum + dice[select[depth]][i], select, dice, score);
        }
    }


    public void combination(int depth,int index, int[] select) {
        if (depth == n / 2) {
            selectA.add(select.clone());
            return;
        }

        for (int i = index; i < n ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                select[depth] = i;
                combination(depth + 1, i +1 ,select);
                visited[i] = false;
            }
        }

    }

}

public class Main {
    public static void main(String[] args) {
        int[][] dice = {{1,2,3,4,5,6}, {2,2,4,4,6,6}};
        int[] result = new Solution().solution(dice);

        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
