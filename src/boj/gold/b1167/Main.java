package boj.gold.b1167;

import java.util.*;

public class Main {

    public static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static class Node {
        int number;
        List<Edge> edges;
        public Node(int number) {
            this.number = number;
            edges = new ArrayList<>();
        }

        public void addEdge(Edge edge) {
            edges.add(edge);
        }
    }

    static int n;
    static Map<Integer, Node> graph;
    static int max = 0;
    static int maxNode = 1;
    static boolean[] visited;

    public static void main(String[] args) {
        input();

        // 첫 번째 DFS를 통해 임의의 노드(1번)에서 가장 먼 노드를 찾음
        visited = new boolean[n + 1];
        visited[1] = true;
        dfs(1, 0);

        // 두 번째 DFS를 통해 첫 번째 DFS에서 찾은 노드에서 가장 먼 노드를 찾음
        visited = new boolean[n + 1];
        visited[maxNode] = true;
        dfs(maxNode, 0);

        System.out.println(max);
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int from = scanner.nextInt();
            graph.put(from, new Node(from));

            while (true) {
                int to = scanner.nextInt();
                if (to == -1) {
                    break;
                }
                int weight = scanner.nextInt();
                graph.get(from).addEdge(new Edge(to, weight));
            }
        }
    }

    private static void dfs(int current, int sum) {
        if (sum > max) {
            max = sum;
            maxNode = current;
        }

        for (Edge edge : graph.get(current).edges) {
            if (visited[edge.to]) {
                continue;
            }
            visited[edge.to] = true;
            dfs(edge.to, sum + edge.weight);
            visited[edge.to] = false;
        }
    }
}
