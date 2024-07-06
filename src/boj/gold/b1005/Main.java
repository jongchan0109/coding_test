package boj.gold.b1005;

import java.util.*;

public class Main {

    static List<List<Integer>> lists;
    static int n;
    static int k;
    static int[] weights;
    static int[] degree;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            n = sc.nextInt();
            k = sc.nextInt();
            weights = new int[n];
            degree = new int[n];

            lists = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                weights[i] = sc.nextInt();
                lists.add(new ArrayList<>());
            }

            for (int j = 0; j < k; j++) {
                int from = sc.nextInt() - 1;
                int to = sc.nextInt() - 1;
                lists.get(from).add(to);
                degree[to]++;
            }

            int target = sc.nextInt() - 1;
            int result = solve(target);

            System.out.println(result);
        }
    }

    private static int solve(int target) {

        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = weights[i];
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Integer next : lists.get(current)) {
                result[next] = Math.max(result[next], result[current] + weights[next]);

                degree[next]--;

                if (degree[next] == 0) {
                    queue.add(next);
                }
            }
        }
        return result[target];
    }
}
