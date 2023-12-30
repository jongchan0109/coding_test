package javasolve.level2.interceptionsystem;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, Comparator.comparingInt(arr -> arr[1]));

        Queue<int[]> queue = new LinkedList<>();
        int answer = 0;

        for (int[] target : targets) {
            queue.offer(target);
        }

        int end = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (current[0] < end) {
                continue;
            }
            end = current[1];
            answer++;
        }


        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] targets = {{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}};
        int result = new Solution().solution(targets);
        System.out.println("result = " + result);
    }
}
