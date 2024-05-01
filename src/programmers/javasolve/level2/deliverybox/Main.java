package programmers.javasolve.level2.deliverybox;

import java.util.*;

class Solution {
    public int solution(int[] order) {
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        int answer = 0;

        for (int i = 1 ; i <= order.length; i++) {

            if (i != order[index]) {
                stack.add(i);
                continue;
            }

            index++;
            answer++;

            while (!stack.isEmpty() && order[index] == stack.peek()) {
                stack.pop();
                index++;
                answer++;
            }
        }

        return answer;
    }
}
public class Main {
    public static void main(String[] args) {
        int[] order = {5,4,3,2,1};
        int result = new Solution().solution(order);

        System.out.println("result = " + result);
    }
}
