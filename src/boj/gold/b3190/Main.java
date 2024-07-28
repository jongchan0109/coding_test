package boj.gold.b3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int k;
    static boolean[][] board;
    static int[] dir;

    //left, up, right, down
    static int[][] move = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};


    static class Pos{
        int x;
        int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Pos pos) {
                return this.x == pos.x && this.y == pos.y;
            }
            return false;
        }
    }


    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solve());
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        board = new boolean[n + 1][n + 1];
        dir = new int[10_001];

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            board[row][col] = true;
        }

        int l = Integer.parseInt(br.readLine());

        for (int i = 0; i < l; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int second = Integer.parseInt(st.nextToken());
            char ch = st.nextToken().charAt(0);

            if (ch == 'L') {
                dir[second] = -1;
            } else {
                dir[second] = 1;
            }
        }

    }

    private static int solve() {

        Deque<Pos> deque = new ArrayDeque<>();

        deque.add(new Pos(1, 1));
        int direction = 102;

        int second = 1;

        while (true) {
            Pos pos = deque.peekFirst();

            if (pos == null) {
                break;
            }

            int nextX = pos.x + move[direction % 4][0];
            int nextY = pos.y + move[direction % 4][1];

            Pos next = new Pos(nextX, nextY);

            if (deque.contains(next) || !range(nextX, nextY)) {
                break;
            }

            if (board[nextX][nextY]) {
                board[nextX][nextY] = false;
            } else {
                deque.removeLast();
            }
            deque.addFirst(next);

            direction += dir[second];
            second++;
        }

        return second;
    }

    private static boolean range(int x, int y) {
        return 0 < x && x <= n && 0 < y && y <= n;
    }

}
