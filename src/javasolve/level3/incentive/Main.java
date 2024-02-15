package javasolve.level3.incentive;

import java.util.*;

class Solution {
    public int solution(int[][] scores) {

        int[] member = {scores[0][0], scores[0][1]};
        List<Integer> incentive = new ArrayList<>();

        Arrays.sort(scores, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);

        int maxScore = 0;

        for (int[] score : scores) {

            if (score[1] < maxScore) {
                if (score[0] == member[0] && score[1] == member[1]) {
                    return -1;
                }

            } else {
                maxScore = score[1];
                incentive.add(score[0] + score[1]);
            }
        }

        incentive.sort(Collections.reverseOrder());

        return incentive.indexOf(member[0] + member[1]) + 1;
    }

}

public class Main {
    public static void main(String[] args) {
        int[][] scores = {{2, 2}, {1, 4}, {3, 2}, {3, 2}, {2, 1}};
        int result = new Solution().solution(scores);
        System.out.println("result = " + result);
    }
}
