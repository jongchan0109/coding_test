package javasolve.level3.mountaincourse;

import java.util.*;

class Solution {

    static List<List<Node>> graph;

    static class Node implements Comparable<Node> {
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }


        @Override
        public int compareTo(Node other) {
            return this.weight - other.weight;
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int weight = path[2];

            if (isGate(from, gates) || isSummit(to, summits)) {
                graph.get(from).add(new Node(to, weight));
            } else if (isGate(to, gates) || isSummit(from, summits)) {
                graph.get(to).add(new Node(from, weight));
            } else {
                graph.get(from).add(new Node(to, weight));
                graph.get(to).add(new Node(from, weight));
            }
        }


        return dijkstra(n, gates, summits);
    }

    public int[] dijkstra(int n, int[] gates, int[] summits) {
        int[] intensity = new int[n + 1];
        Queue<Node> queue = new LinkedList<>();
        Arrays.fill(intensity, Integer.MAX_VALUE);
        int[] answer = new int[2];

        for (int gate: gates) {
            queue.add(new Node(gate,0));
            intensity[gate] = 0;
        }

        while(!queue.isEmpty()) {
            Node current = queue.poll();

            if (intensity[current.index] < current.weight) {
                continue;
            }

            for(int i=0; i<graph.get(current.index).size(); i++){
                Node next = graph.get(current.index).get(i);

                int c = Math.max(intensity[current.index], next.weight);
                if(intensity[next.index] > c){
                    intensity[next.index] = c;
                    queue.add(new Node(next.index, c));
                }
            }

        }
        answer[1] = Integer.MAX_VALUE;
        Arrays.sort(summits);

        for (int summit : summits) {
            if (intensity[summit] < answer[1]) {
                answer[1] = intensity[summit];
                answer[0] = summit;
            }
        }
        return answer;
    }

    boolean isSummit(int v, int[] summits) {
        return isIn(v, summits);
    }

    boolean isGate(int v, int[] gates) {
        return isIn(v, gates);
    }

    boolean isIn(int v, int[] array) {
        for (int i : array) {
            if (i == v) {
                return true;
            }
        }
        return false;
    }

}

public class Main {
    public static void main(String[] args) {
        int n = 6;
        int[][] paths = {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4,}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}};
        int[] gates = {1, 3};
        int[] summits = {5};

        int[] result = new Solution().solution(n, paths, gates, summits);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}