package boj.gold.b1238;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static int N;
    static int M;
    static int X;
    static int[][] adj;
    static final int INF = 1000000;

    public static void main(String[] args) {
        input();

        int[] distToX = dijkstra(X); // X에서 다른 노드로 가는 최단 거리
        int maxTime = 0;

        for (int i = 1; i <= N; i++) {
            if (i == X) continue;
            int[] distFromI = dijkstra(i); // i에서 다른 모든 마을로 가는 최단 거리
            int roundTripTime = distFromI[X] + distToX[i];
            if (roundTripTime > maxTime) {
                maxTime = roundTripTime;
            }
        }

        System.out.println(maxTime);
    }

    private static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentNode = current.node;

            if (visited[currentNode]) continue;
            visited[currentNode] = true;

            for (int i = 1; i <= N; i++) {
                if (adj[currentNode][i] != INF && dist[i] > dist[currentNode] + adj[currentNode][i]) {
                    dist[i] = dist[currentNode] + adj[currentNode][i];
                    pq.add(new Node(i, dist[i]));
                }
            }
        }

        return dist;
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        X = scanner.nextInt();

        adj = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(adj[i], INF);
        }

        for (int i = 0; i < M; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int weight = scanner.nextInt();
            adj[from][to] = weight;
        }

        scanner.close();
    }

    static class Node implements Comparable<Node> {
        int node;
        int cost;

        Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
