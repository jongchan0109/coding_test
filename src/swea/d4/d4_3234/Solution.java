package swea.d4.d4_3234;

import java.util.*;

public class Solution {

    static int n;
    static int[] arr;
    static int sum;
    static boolean[] visited;
    static int[] factorial;

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();


        for(int test_case = 1; test_case <= T; test_case++) {
            n = sc.nextInt();
            arr = new int[n];
            sum = 0;
            visited = new boolean[n];
            factorial = new int[n];


            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            fact(0);

            System.out.println("#" + test_case + " " + sum);
        }
    }

    public static void fact(int depth) {
        if (depth == n) {
            DFS(0,0,0);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                factorial[depth] = arr[i];
                fact(depth + 1);
                visited[i] = false;
            }
        }

    }

    public static void DFS(int rightSum, int leftSum, int depth) {

        if (depth == n) {
            if (leftSum >= rightSum) {
                sum++;
            }
            return;
        }

        if (rightSum > leftSum) {
            return;
        }
        DFS(rightSum + factorial[depth], leftSum, depth + 1);
        DFS(rightSum, leftSum + factorial[depth], depth + 1);

    }

}


