package javasolve.level3.makezero;

import java.util.*;

class Solution {

    static class Node {
        long weight;
        Set<Integer> neighbors;

        public Node(int weight) {
            this.weight = weight;
            neighbors = new HashSet<>();
        }
    }

    public long solution(int[] a, int[][] edges) {
        long answer = 0L;
        long sum = 0;
        for (int weight : a) {
            sum += weight;
        }
        if (sum != 0) {
            return -1L;
        }

        Node[] nodes = new Node[a.length];
        for (int i = 0; i < a.length; i++) {
            nodes[i] = new Node(a[i]);
        }

        for (int[] edge : edges) {
            nodes[edge[0]].neighbors.add(edge[1]);
            nodes[edge[1]].neighbors.add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[a.length];

        for (int i = 0; i < a.length; i++) {
            if (nodes[i].neighbors.size() == 1) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int nodeIndex = queue.poll();
            visited[nodeIndex] = true;

            Node node = nodes[nodeIndex];
            for (int neighborIndex : node.neighbors) {
                if (!visited[neighborIndex]) {
                    Node neighbor = nodes[neighborIndex];
                    answer += Math.abs(node.weight);
                    neighbor.weight += node.weight;
                    neighbor.neighbors.remove(nodeIndex); // 이웃 노드에서 현재 노드를 제거
                    if (neighbor.neighbors.size() == 1) {
                        queue.add(neighborIndex);
                    }
                }
            }
        }

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] a = {-5, 0, 2, 1, 2};
        int[][] edges = {{0, 1}, {3, 4}, {2, 3}, {0, 3}};
        long result = new Solution().solution(a, edges);
        System.out.println("result = " + result);
    }
}
