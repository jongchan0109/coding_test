package swea.d4.d4_19004;

import java.util.*;

import java.util.Scanner;

class Solution
{

    static final int MAX = 987654321;

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();


        for(int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int result = MAX;

            int[][] arr = new int[n][n];
            int[][] map = new int[n][n];

            HashMap<Integer, List<int[]>> hash = new HashMap<>();

            for (int i = 1; i <= k; i++) {
                hash.put(i, new ArrayList<>());
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                    map[i][j] = MAX;
                    hash.get(arr[i][j]).add(new int[]{i,j});
                }
            }

            for (int[] cu: hash.get(1)) {
                map[cu[0]][cu[1]] = 0;
            }

            for (int i = 1; i < k; i++) {
                List<int[]> current = hash.get(i);
                List<int[]> next = hash.get(i + 1);

                for (int[] cur: current) {
                    for (int[] ne: next) {
                        map[ne[0]][ne[1]] = Math.min(map[ne[0]][ne[1]], map[cur[0]][cur[1]] + Math.abs(cur[0] - ne[0]) + Math.abs(cur[1] - ne[1]));
                    }
                }
            }



            List<int[]> last = hash.get(k);

            for (int[] la: last) {
                result = Math.min(map[la[0]][la[1]], result);
            }

            if (result == MAX) {
                System.out.println("#" + test_case + " " + -1);
                continue;
            }

            System.out.println("#" + test_case + " " + result);

        }
    }
}
