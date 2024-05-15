package swea.d4.d4_1251;


import java.util.*;

public class Solution {

    static int[] parent;

    public static class Edge implements Comparable<Edge>{
        int from;
        int to;
        long weight;

        public Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.weight == o.weight) {
                return 0;
            } else if (this.weight > o.weight) {
                return 1;
            }
            return -1;
        }
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int[] xPos = new int[n];
            int[] yPos = new int[n];
            parent = new int[n];
            List<Edge> edges = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                xPos[i] = sc.nextInt();
                parent[i] = i;
            }
            for (int i = 0; i < n; i++) {
                yPos[i] = sc.nextInt();
            }

            for (int i = 0; i < n-1; i++) {
                for (int j = i + 1; j < n; j++) {
                    long dis = (long)Math.pow(xPos[i] - xPos[j] , 2) + (long)Math.pow(yPos[i] - yPos[j], 2);
                    edges.add(new Edge(i,j,dis));
                }
            }

            Collections.sort(edges);

            long sum = 0;
            int index = 0;
            int count = 0;

            while (count < n - 1) {
                Edge edge = edges.get(index++);
                int from = edge.from;
                int to = edge.to;

                if (find(from) == find(to)) {
                    continue;
                }

                union(from, to);
                sum += edge.weight;
                count++;
            }

            double tax = sc.nextDouble();

            long result = (long)(sum * tax + 0.5);

            System.out.println("#" + test_case + " " + result);

        }
    }

    public static void union(int element1, int element2) {
        int findX = find(element1);
        int findY = find(element2);
        parent[findY] = findX;
    }
    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);

    }

}

