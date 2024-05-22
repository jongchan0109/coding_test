package boj.gold.b2213;

import java.util.*;

public class Main {

    static class TreeNode {
        int weight;
        List<Integer> children;

        TreeNode(int weight) {
            this.weight = weight;
            this.children = new ArrayList<>();
        }
    }

    static TreeNode[] tree;
    static int[] dp1, dp2;
    static boolean[] visited;
    static List<Integer> result;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 첫 번째 줄: 트리의 정점의 수
        int n = scanner.nextInt();
        tree = new TreeNode[n + 1];
        dp1 = new int[n + 1];
        dp2 = new int[n + 1];
        visited = new boolean[n + 1];
        result = new ArrayList<>();

        // 두 번째 줄: 각 정점의 가중치
        for (int i = 1; i <= n; i++) {
            int weight = scanner.nextInt();
            tree[i] = new TreeNode(weight);
        }

        // 간선 입력
        for (int i = 1; i < n; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            tree[u].children.add(v);
            tree[v].children.add(u);
        }

        scanner.close();

        // 루트 노드를 기준으로 DFS 및 DP 계산
        dfs(1);

        // 최대 독립 집합의 크기
        int maxWeight = Math.max(dp1[1], dp2[1]);
        System.out.println(maxWeight);

        visited = new boolean[n + 1];

        findSolution(1, dp1[1] >= dp2[1]);
        Collections.sort(result);
        for (int node : result) {
            System.out.print(node + " ");
        }
    }



    static void dfs(int node) {
        visited[node] = true;
        dp1[node] = tree[node].weight;
        dp2[node] = 0;

        for (int child : tree[node].children) {
            if (!visited[child]) {
                dfs(child);
                dp1[node] += dp2[child];
                dp2[node] += Math.max(dp1[child], dp2[child]);
            }
        }
    }

    static void findSolution(int node, boolean include) {
        visited[node] = true;
        if (include) {
            result.add(node);
        }

        for (int child : tree[node].children) {
            if (!visited[child]) {
                if (include) {
                    findSolution(child, false);
                } else {
                    findSolution(child, dp1[child] >= dp2[child]);
                }
            }
        }
    }
}
