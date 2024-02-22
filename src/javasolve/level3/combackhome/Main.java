package javasolve.level3.combackhome;

import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];

        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] dest = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
            dest[i] = -1;
        }

        for (int[] road : roads) {
            map.get(road[0]).add(road[1]);
            map.get(road[1]).add(road[0]);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{destination, 0});
        boolean[] visited = new boolean[n + 1];
        visited[destination] = true;
        dest[destination] = 0;

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            List<Integer> list = map.get(current[0]);

            for (Integer next : list) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[]{next, current[1] + 1});
                    dest[next] = current[1] + 1;
                }
            }
        }

        for (int i = 0; i < sources.length; i++) {
            answer[i] = dest[sources[i]];
        }

        return answer;
    }

}

public class Main {
    public static void main(String[] args) {
        int n = 3;
        int[][] roads = {{1, 2}, {2, 3}};
        int[] sources = {2, 3};
        int destination = 1;

        int[] result = new Solution().solution(n, roads, sources, destination);
        for (int i : result) {
            System.out.print(i + " ");
        }

    }
}
