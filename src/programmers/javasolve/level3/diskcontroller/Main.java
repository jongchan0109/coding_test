package programmers.javasolve.level3.diskcontroller;

import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int n = jobs.length;
        int time = 0;
        int sum = 0;

        PriorityQueue<int[]> pq1 = new PriorityQueue<>(Comparator.comparingInt(o2 -> o2[0]));
        PriorityQueue<int[]> pq2 = new PriorityQueue<>(Comparator.comparingInt(o2 -> o2[1]));
        pq1.addAll(Arrays.asList(jobs));


        while (!pq1.isEmpty() || !pq2.isEmpty()) {

            while(!pq1.isEmpty() && pq1.peek()[0] <= time) {
                pq2.add(pq1.poll());
            }

            if (pq2.isEmpty()) {
                time++;
            } else {
                int[] job = pq2.poll();
                sum += time + job[1] -  job[0];
                time += job[1];
            }

        }
        return sum / n;
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
        int result = new Solution().solution(jobs);
        System.out.println("result = " + result);
    }
}
