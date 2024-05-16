package swea.d4.d4_1219;

import java.util.*;

public class Solution {

    static Map<Integer, List<Integer>> map;
    static boolean canReach;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        for(int test_case = 1; test_case <= 10; test_case++) {

            int t = sc.nextInt();
            int n = sc.nextInt();
            map = new HashMap<>();
            visited = new boolean[101];
            canReach = false;

            for (int i = 0; i <= 99; i++) {
                map.put(i, new ArrayList<>());
            }

            for (int i = 0; i < n; i++) {
                int from = sc.nextInt();
                int to = sc.nextInt();
                map.get(from).add(to);
            }


            DFS(0);
            if (canReach) {
                System.out.println("#" + t + " " + 1);
            } else {
                System.out.println("#" + t + " " + 0);
            }

        }
    }

    public static void DFS(int current) {
        if (canReach) {
            return;
        }
        if (current == 99) {
            canReach = true;
            return;
        }
        for (int next: map.get(current)) {
            if (visited[next]) {
                continue;
            }
            visited[next] = true;
            DFS(next);
            visited[next] = false;
        }

    }

}
