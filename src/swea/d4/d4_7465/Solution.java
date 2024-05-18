package swea.d4.d4_7465;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {

    static int[] parent;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            boolean[][] map = new boolean[n + 1][n + 1];
            Set<Integer> set = new HashSet<>();
            parent = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < m; i++) {
                int from = sc.nextInt();
                int to = sc.nextInt();
                map[from][to] = true;
                map[to][from] = true;
            }

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    if(map[i][j]) {
                        union(i,j);
                    }
                }
            }

            for (int i = 1; i <= n; i++) {
                set.add(find(i));
            }

            System.out.println("#" + test_case + " " + set.size());

        }
    }

    public static void union(int i, int j) {
        int findI = find(i);
        int findJ = find(j);
        if (findI != findJ) {
            parent[findJ] = findI;
        }
    }

    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
}

