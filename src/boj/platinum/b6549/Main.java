package boj.platinum.b6549;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int n;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        while(true) {
            String s = br.readLine();
            if (s.equals("0")) {
                break;
            }
            st = new StringTokenizer(s);
            n = Integer.parseInt(st.nextToken());
            arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            sb.append(getArea()).append("\n");
        }

        System.out.println(sb);
    }

    private static long getArea() {

        long area = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {

            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int height = arr[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                area = Math.max(area, (long)height * width);
            }
            stack.push(i);
        }

        while(!stack.isEmpty()) {
            int height = arr[stack.pop()];
            int width = stack.isEmpty() ? n : n - stack.peek() - 1;
            area = Math.max(area, (long)height * width);
        }

        return area;
    }

}
