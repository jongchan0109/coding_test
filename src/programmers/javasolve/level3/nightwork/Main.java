package programmers.javasolve.level3.nightwork;

import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0L;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int work: works) {
            pq.add(work);
        }

        for (int i = 0; i < n; i++) {
            int num = pq.remove();
            if (num <= 0) {
                return 0L;
            }
            pq.add(num - 1);
        }

        while(!pq.isEmpty()) {
            int num = pq.remove();
            answer += (long) num * num;
        }


        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] works = {4,3,3};
        int n = 4;
        long result = new Solution().solution(n, works);
        System.out.println("result = " + result);
    }
}
