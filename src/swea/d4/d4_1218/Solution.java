package swea.d4.d4_1218;

import java.util.Scanner;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {

            int len = sc.nextInt();
            String str = sc.next();

            int result = solve(str, len);

            System.out.println("#" + test_case + " " + result);

        }
    }

    public static int solve(String str, int len) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);

            if (c == '(' || c == '{' || c == '[' || c== '<') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return 0;
                }
                if (c == ')') {
                    if (stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return 0;
                    }
                } else if(c == '}') {
                    if (stack.peek() == '{') {
                        stack.pop();
                    } else {
                        return 0;
                    }
                } else if(c == ']') {
                    if (stack.peek() == '[') {
                        stack.pop();
                    } else{
                        return 0;
                    }
                } else if(c == '>') {
                    if (stack.peek() == '<') {
                        stack.pop();
                    } else {
                        return 0;
                    }
                }
            }


        }

        if (stack.isEmpty()) {
            return 1;
        }
        return 0;


    }
}
