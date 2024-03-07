package javasolve.level3.oneonezero;

import java.util.*;

class Solution {

    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for (int i = 0; i < s.length; i++) {
            Stack<Character> stack = new Stack<>();

            int count = 0;

            for (int j = 0; j < s[i].length(); j++) {
                char three = s[i].charAt(j);

                if (stack.size() > 1) {
                    char two = stack.pop();
                    char one = stack.pop();

                    if (one == '1' && two == '1' && three == '0') {
                        count++;
                    } else {
                        stack.push(one);
                        stack.push(two);
                        stack.push(three);
                    }

                } else {
                    stack.push(three);
                }

            }

            int index = stack.size();
            boolean flag = false;
            StringBuilder sb = new StringBuilder();

            while (!stack.isEmpty()) {
                if (!flag) {
                    if (stack.peek() == '1') {
                        index--;
                    } else {
                        flag = true;
                    }
                }
                sb.insert(0,stack.pop());
            }

            while (count!= 0) {
                sb.insert(index,"110");
                count--;
            }
            answer[i] = sb.toString();
        }

        return answer;
    }
}


public class Main {
    public static void main(String[] args) {
        String[] s = {"1110", "100111100", "0111111010", "1100111011101001"};
        String[] result = new Solution().solution(s);
        for (String string : result) {
            System.out.println("string = " + string);
        }
    }
}
