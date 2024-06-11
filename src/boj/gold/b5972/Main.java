package boj.gold.b5972;

import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {

        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }

    }

    static int n;
    static List<List<Node>> graph;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();
        int m = scanner.nextInt();


        graph = new ArrayList<>();


        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int weight = scanner.nextInt();

            graph.get(from).add(new Node(to, weight));
            graph.get(to).add(new Node(from, weight));

        }

        System.out.println(dijkstra());
    }

    private static int dijkstra() {

        boolean[] visited = new boolean[n + 1];
        int[] d = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0));
        d[1] = 0;


        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visited[node.to]) {
                continue;
            }
            visited[node.to] = true;

            for (Node neighbor : graph.get(node.to)) {
                if (d[neighbor.to] > d[node.to] + neighbor.weight) {
                    d[neighbor.to] = d[node.to] + neighbor.weight;
                    pq.add(new Node(neighbor.to, d[neighbor.to]));
                }
            }
        }
        return d[n];
    }
}
