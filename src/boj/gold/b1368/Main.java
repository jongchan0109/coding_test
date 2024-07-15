package boj.gold.b1368;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static int n;
    static int[] weights;

    static int[] parent;

    static PriorityQueue<Edge> pq;

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }


        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) {
        init();
        System.out.println(solve());
    }

    private static void init() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        weights = new int[n + 1];
        parent = new int[n + 1];
        pq = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {
            weights[i] = scanner.nextInt();
            parent[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int weight = scanner.nextInt();

                if (i == j) {
                    pq.add(new Edge(0, i, weights[i]));
                } else if (i > j) {
                    pq.add(new Edge(i, j, weight));
                }

            }
        }
    }

    private static int solve() {
        int sum = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                sum += edge.weight;
            }
        }

        return sum;
    }


    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

}
