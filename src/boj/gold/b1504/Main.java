package boj.gold.b1504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int end;
        int weight;


        Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int n;
    static int v1;
    static int v2;

    static List<List<Edge>> graph;

    static final int INF = 98765432;

    public static void main(String[] args) throws IOException {
        input();

        int sum1 = 0;
        sum1 += dijkstra(1, v1);
        sum1 += dijkstra(v1, v2);
        sum1 += dijkstra(v2, n);

        int sum2 = 0;
        sum2 += dijkstra(1, v2);
        sum2 += dijkstra(v2, v1);
        sum2 += dijkstra(v1, n);

        int result = Math.min(sum1, sum2);
        if (result >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }


        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
    }

    private static int dijkstra(int start, int end) {
        int[] d = new int[n + 1];
        Arrays.fill(d, INF);
        d[start] = 0;

        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));


        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int cur = edge.end;
            
            if (!visited[cur]) {
                visited[cur] = true;
                for (Edge e : graph.get(cur)) {
                    if (!visited[e.end] && d[e.end] > d[cur] + e.weight) {
                        d[e.end] = d[cur] + e.weight;
                        pq.add(new Edge(e.end, d[e.end]));
                    }
                }

            }
        }
        return d[end];
    }
}
