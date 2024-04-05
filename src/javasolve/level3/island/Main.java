package javasolve.level3.island;

import java.util.*;

class Solution {


    static int[] parent;

    public int solution(int n, int[][] costs) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        int answer = 0;

        Arrays.sort(costs, Comparator.comparingInt(c -> c[2]));

        for (int[] cost : costs) {
            int x = cost[0];
            int y = cost[1];

            int findX = find(x);
            int findY = find(y);

            if (findX != findY) {
                union(x, y);
                answer += cost[2];
            }
        }


        return answer;
    }

    public int find(int num) {
        if (parent[num] == num) {
            return num;
        }
        return parent[num] = find(parent[num]);
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[x] = y;
        }
    }
}


public class Main {
    public static void main(String[] args) {
        int n = 4;
        int[][] costs = {{0,1,1}, {0,2,2}, {1,2,5}, {1,3,1}, {2,3,8}};
        int result = new Solution().solution(n,costs);
        System.out.println("result = " + result);
    }
}
