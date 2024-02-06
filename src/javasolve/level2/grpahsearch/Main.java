package javasolve.level2.grpahsearch;

import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        boolean[][] graph = new boolean[n+1][n+1];
        int answer = 200;

        for (int[] wire: wires) {
            connect(graph, wire[0], wire[1]);
        }

        for (int[] wire: wires) {
            disconnect(graph, wire[0], wire[1]);
            int[] result = BFS(graph, n);
            connect(graph, wire[0], wire[1]);

            int resultAbs = Math.abs(result[0] - result[1]);
            if (resultAbs < answer) {
                answer = resultAbs;
            }
        }
        return answer;
    }

    int[] BFS(boolean[][] graph, int n) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        int[] answer = {0,0};

        queue.offer(1);
        visited[1] = true;

        while(!queue.isEmpty()) {
            int current = queue.poll();

            for (int i = 1; i <=n ;i++) {
                if (graph[current][i] && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
            answer[0]++;
        }

        int start2;

        for (start2 = 1; start2 <= n ; start2++) {
            if (!visited[start2]) {
                break;
            }
        }

        queue.offer(start2);
        visited[start2] = true;

        while(!queue.isEmpty()) {
            int current = queue.poll();

            for (int i = 1; i <=n ;i++) {
                if (graph[current][i] && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
            answer[1]++;
        }
        return answer;
    }


    void disconnect(boolean[][] graph, int from, int to) {
        graph[from][to] = false;
        graph[to][from] = false;
    }

    void connect(boolean[][] graph, int from, int to) {
        graph[from][to] = true;
        graph[to][from] = true;
    }

}


public class Main {
    public static void main(String[] args) {
        int n = 4;
        int[][] wires = {{1,2}, {2,3}, {3,4}};
        int result = new Solution().solution(n,wires);

        System.out.println("result = " + result);
    }
}
