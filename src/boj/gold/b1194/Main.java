package boj.gold.b1194;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int n;
    static int m;

    static int[] start;

    static char[][] board;

    static class People {
        int x;
        int y;
        int count;
        String key;

        People(int x, int y, int count, String key) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.key = key;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        int result = bfs();
        System.out.println(result);
    }

    private static void input() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] str = bf.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        board = new char[n][m];
        start = new int[2];

        for (int i = 0; i < n; i++) {
            String string = bf.readLine();

            for (int j = 0; j < m; j++) {
                board[i][j] = string.charAt(j);
                if (board[i][j] == '0') {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }
    }

    public static int bfs() {

        boolean[][][] visited = new boolean[n][m][64];// 2**6

        Queue<People> queue = new LinkedList<>();
        queue.add(new People(start[0], start[1], 0, "000000"));
        visited[start[0]][start[1]][0] = true;

        while(!queue.isEmpty()) {
            People people = queue.poll();

            if (board[people.x][people.y] == '1') {
                return people.count;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = people.x + dx[i];
                int nextY = people.y + dy[i];

                if (range(nextX, nextY)) {
                    char ch = board[nextX][nextY];

                    if ('A' <= ch && ch <= 'F') { // 문이 있는 경우
                        if (people.key.charAt(ch - 'A') == '1') { //열쇠가 이미 있던 경우
                            int key = Integer.parseInt(people.key, 2);
                            if (!visited[nextX][nextY][key]) {
                                queue.add(new People(nextX, nextY, people.count + 1, people.key));
                                visited[nextX][nextY][key] = true;
                            }
                        }
                    } else if('a' <= ch && ch <= 'f') { //열쇠가 있는 경우
                        String key = people.key.substring(0,ch - 'a') + "1" + people.key.substring(ch - 'a' + 1);
                        int intKey = Integer.parseInt(key, 2);

                        if (!visited[nextX][nextY][intKey]) {
                            queue.add(new People(nextX, nextY, people.count + 1, key));
                            visited[nextX][nextY][intKey] = true;
                        }

                    } else if(ch =='.' || ch =='0' || ch == '1') {
                        int intKey = Integer.parseInt(people.key, 2);
                        if (!visited[nextX][nextY][intKey]) {
                            queue.add(new People(nextX, nextY, people.count + 1, people.key));
                            visited[nextX][nextY][intKey] = true;
                        }
                    }
                }

            }
        }
        return -1;
    }

    private static boolean range(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}

