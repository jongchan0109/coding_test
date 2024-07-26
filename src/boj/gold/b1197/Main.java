package boj.gold.b1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int v;
    static int e;

    static PriorityQueue<Edge> pq;
    static int[] parent;

    public static class Edge implements Comparable<Edge> {


        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        pq = new PriorityQueue<>();
        input();
        System.out.println(solve());
    }

    private static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        parent = new int[v+1];

        for (int i = 1; i < v + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(in.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            pq.add(new Edge(from, to, weight));
        }

    }

    private static int solve() {

        int sum = 0;

        while(!pq.isEmpty()) {
            Edge edge = pq.poll();

            int from = Math.min(edge.from, edge.to);
            int to = Math.max(edge.from, edge.to);

            if (find(from) == find(to)) {
                continue;
            }
            union(from, to);
            sum += edge.weight;
        }

        return sum;
    }

    private static int find(int vertex) {
        if (parent[vertex] == vertex) {
            return vertex;
        }
        return parent[vertex] = find(parent[vertex]);
    }

    private static void union(int vertex1, int vertex2) {
        int parentV1 = find(vertex1);
        int parentV2 = find(vertex2);

        if (parentV1 != parentV2) {
            parent[parentV2] = parentV1;
        }
    }
}
