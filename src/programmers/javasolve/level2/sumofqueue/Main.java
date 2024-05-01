package programmers.javasolve.level2.sumofqueue;

import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        long sum1 = 0L;
        long sum2 = 0L;

        for (int num: queue1) {
            q1.offer(num);
            sum1 += num;
        }
        for (int num: queue2) {
            q2.offer(num);
            sum2 += num;
        }

        while (sum1 != sum2) {
            if (answer > queue1.length * 3|| q1.isEmpty() || q2.isEmpty()) {
                return -1;
            }
            int remove;
            if (sum1 > sum2) {
                remove = q1.poll();
                sum2 += remove;
                sum1 -= remove;
                q2.offer(remove);
            } else {
                remove = q2.poll();
                sum1 += remove;
                sum2 -= remove;
                q1.offer(remove);
            }
            answer++;
        }
        return answer;
    }
}
public class Main {
    public static void main(String[] args) {
        int[] queue1 = {1,1,1,1,1,1};
        int[] queue2 = {1,1,1,1,11,1};

        int result = new Solution().solution(queue1,queue2);

        System.out.println("result = " + result);
    }
}
