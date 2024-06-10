package boj.gold.b9019;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static class Number {
        int num;
        StringBuilder sb;

        public Number(int num) {
            this.num = num;
            sb = new StringBuilder();
        }
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();

        for (int i = 0; i < T; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();

            String result = BFS(start, end);
            System.out.println(result);


//            int num = start;
//            for (int j = 0; j < result.length(); j++) {
//
//                if (result.charAt(j) == 'L') {
//                    num = l(num);
//                    System.out.println("L" + " num = " + num);
//                } else if (result.charAt(j) == 'R') {
//                    num = r(num);
//                    System.out.println("R" + " num = " + num);
//                } else if(result.charAt(j) == 'D') {
//                    num = d(num);
//                    System.out.println("D" + " num = " + num);
//                } else {
//                    num = s(num);
//                    System.out.println("S" + " num = " + num);
//                }
//
//            }


        }

    }

    private static String BFS(int start, int end) {
        Queue<Number> queue = new LinkedList<>();
        queue.add(new Number(start));
        boolean[] visited = new boolean[10000];
        visited[start] = true;

        while (!queue.isEmpty()) {

            Number poll = queue.poll();
            if (poll.num == end) {
                return poll.sb.toString();
            }

            int num = poll.num;


            int dNum = d(num);

            if (!visited[dNum]) {
                visited[dNum] = true;
                Number number = new Number(dNum);
                number.sb.append(poll.sb).append("D");
                queue.add(number);
            }

            int sNum = s(num);

            if (!visited[sNum]) {
                visited[sNum] = true;
                Number number = new Number(sNum);
                number.sb.append(poll.sb).append("S");
                queue.add(number);
            }

            int lNum = l(num);
            if (!visited[lNum]) {
                visited[lNum] = true;
                Number number = new Number(lNum);
                number.sb.append(poll.sb).append("L");
                queue.add(number);
            }

            int rNum = r(num);
            if (!visited[rNum]) {
                visited[rNum] = true;
                Number number = new Number(rNum);
                number.sb.append(poll.sb).append("R");
                queue.add(number);
            }

        }

        return null;

    }

    private static int d(int num) {
        num *= 2;

        if (num > 9999) {
            num %= 10000;
        }
        return num;

    }

    private static int s(int num) {
        if (num == 0) {
            return 9999;
        }
        return num - 1;
    }

    private static int l(int num) {
        int result = 0;

        int[] arr = toArray(num);

        int temp = arr[0];

        for (int i = 0; i < 3; i++) {
            arr[i] = arr[i + 1];
        }
        arr[3] = temp;

        for (int i = 0; i < 3; i++) {
            result += arr[i];
            result *= 10;
        }
        result += arr[3];
        return result;
    }

    private static int r(int num) {
        int result = 0;

        int[] arr = toArray(num);

        int temp = arr[3];

        for (int i = 2; i >= 0; i--) {
            arr[i + 1] = arr[i];
        }
        arr[0] = temp;

        for (int i = 0; i < 3; i++) {
            result += arr[i];
            result *= 10;
        }
        result += arr[3];
        return result;

    }

    private static int[] toArray(int num) {
        int[] arr = new int[4];
        int mod = 1000;

        for (int i = 0; i < 4; i++) {
            arr[i] = num / mod;
            num %= mod;
            mod /= 10;
        }
        return arr;
    }


}
