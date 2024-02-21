package javasolve.level3.lighthouse;

import java.util.*;

class Solution {
    public int solution(int n, int[][] lighthouse) {
        boolean[] light = new boolean[n + 1];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int[] house : lighthouse) {
            int n1 = house[0];
            int n2 = house[1];
            map.get(n1).add(n2);
            map.get(n2).add(n1);
        }

        for (int i = 1; i <= n; i++) {
            if (map.get(i).size() == 1) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer n1 = queue.poll();

            if (map.get(n1).isEmpty()) {
                continue;
            }

            if (map.get(n1).size() != 1) {
                queue.add(n1);
                continue;
            }
            Integer n2 = map.get(n1).get(0);

            map.get(n1).remove(n2);
            map.get(n2).remove(n1);

            if (!light[n1]) {
                light[n2] = true;
            }

            queue.add(n2);
        }
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            if (light[i]) {
                answer++;
            }
        }
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        int n = 8;
        int[][] lighthouse = {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}};
        int result = new Solution().solution(n, lighthouse);
        System.out.println("result = " + result);
    }
}
