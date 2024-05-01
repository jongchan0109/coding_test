package programmers.javasolve.level3.doublequeue;

import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int[] answer = new int[2];

        for (String o : operations) {
            String[] operation = o.split(" ");
            String command = operation[0];
            int num = Integer.parseInt(operation[1]);

            if (command.equals("I")) {
                pq.add(num);
            } else {
                if (pq.isEmpty()) {
                    continue;
                }
                if (num == -1) {
                    pq.remove();
                } else {
                    PriorityQueue<Integer> temp = new PriorityQueue<>();
                    for (int i = 0; i < pq.size() - 1; i++) {
                        temp.add(pq.remove());
                    }
                    pq = temp;
                }
            }

        }

        if (pq.isEmpty()) {
            return new int[]{0, 0};
        } else {
            answer[1] = pq.peek();

            while (!pq.isEmpty()) {
                answer[0] = pq.remove();
            }

        }

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        String[] operations = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        int[] result = new Solution().solution(operations);
        System.out.println("result[0] = " + result[0] + " result[1] = " + result[1]);
    }
}
