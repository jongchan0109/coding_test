package boj.gold.b17822;

import java.util.*;

public class Main {

    static int n;
    static int m;
    static int t;

    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {- 1, 0, 1, 0};


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        t = scanner.nextInt();

        map = new int[n + 1][m];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = scanner.nextInt();
            }
        }


        while (t != 0) {
            int x = scanner.nextInt();
            int d = scanner.nextInt();
            int k = scanner.nextInt();

            int row = x;
            if (d == 0) {
                while (row <= n) {
                    leftRotate(row, k);
                    row += x;
                }
            } else {
                while (row <= n) {
                    rightRotate(row, k);
                    row += x;
                }
            }



            if (!erase()) {
                mean();
            }
            t--;
        }
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                sum += map[i][j];
            }
        }
        System.out.println(sum);

    }

    public static void mean() {


        double avg = 0.0;
        int count = 0;

        for (int i = 1; i <= n; i++) {

            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                avg += map[i][j];
                count++;
            }
        }
        avg /= count;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                if (map[i][j] > avg) {
                    map[i][j] -= 1;
                }
                else if(map[i][j] < avg) {
                    map[i][j] += 1;
                }
            }
        }

    }


    public static void rightRotate(int row, int k) {

        for (int i = 0; i < k; i++) {
            int temp = map[row][0];

            for (int j = 1; j < m; j++) {
                map[row][j - 1] = map[row][j];
            }
            map[row][m - 1] = temp;
        }
    }


    public static void leftRotate(int row, int k) {
        for (int i = 0; i < k; i++) {
            int temp = map[row][m - 1];

            for (int j = m - 1; j > 0; j--) {
                map[row][j] = map[row][j - 1];
            }
            map[row][0] = temp;
        }

    }

    public static boolean erase() {

        boolean[][] visited = new boolean[n + 1][m];
        Queue<int[]> queue = new LinkedList<>();
        boolean flag = false;
        List<int[]> remove = new ArrayList<>();

        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] || map[i][j] == 0) {
                    continue;
                }
                visited[i][j] = true;
                queue.add(new int[]{i, j});

                boolean removeFlag = false;

                while (!queue.isEmpty()) {
                    int[] pop = queue.poll();

                    for (int k = 0; k < 4; k++) {
                        int nextX = pop[0] + dx[k];
                        int nextY = pop[1] + dy[k];

                        if (nextX < 1 || nextX > n) {
                            continue;
                        }
                        if (nextY < 0) {
                            nextY = m - 1;
                        } else if(nextY > m - 1) {
                            nextY = 0;
                        }

                        if (visited[nextX][nextY]) {
                            continue;
                        }
                        if (map[pop[0]][pop[1]] == map[nextX][nextY]) {
                            remove.add(new int[]{nextX, nextY});
                            queue.add(new int[]{nextX, nextY});
                            visited[nextX][nextY] = true;
                            flag = true;
                            removeFlag = true;
                        }

                    }

                }
                if (removeFlag) {
                    remove.add(new int[]{i,j});
                }

            }
        }

        for (int[] removed : remove) {
            map[removed[0]][removed[1]] = 0;
        }

        return flag;
    }

}