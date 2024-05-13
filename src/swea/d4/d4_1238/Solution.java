package swea.d4.d4_1238;

import java.util.*;

public class Solution {

    static class Point {
        int index;
        int count;

        Point(int index, int count) {
            this.index = index;
            this.count = count;
        }
    }


    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        for(int test_case = 1; test_case <= 10; test_case++) {

            int n = sc.nextInt();
            int start = sc.nextInt();
            Map<Integer, ArrayList<Integer>> map = new HashMap<>();
            boolean[] visited = new boolean[101];

            for (int i = 1; i <= 100; i++) {
                map.put(i, new ArrayList<>());
            }

            for (int i = 0; i < n / 2; i++) {
                int from = sc.nextInt();
                int to = sc.nextInt();
                map.get(from).add(to);
            }

            Queue<Point> queue = new LinkedList<>();

            queue.add(new Point(start,0));
            visited[start] = true;
            int depth = -1;
            int result = 0;

            while(!queue.isEmpty()) {
                Point current = queue.remove();

                if (current.count > depth) {
                    depth = current.count;
                    result = current.index;
                } else if(current.count == depth) {
                    result = Math.max(result, current.index);
                }

                List<Integer> list = map.get(current.index);

                for (int num: list) {
                    if (!visited[num]) {
                        queue.add(new Point(num, current.count + 1));
                        visited[num] = true;
                    }
                }

            }

            System.out.println("#" + test_case + " " + result);

        }
    }
}
