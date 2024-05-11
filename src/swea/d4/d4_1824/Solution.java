package swea.d4.d4_1824;

import java.util.Scanner;

public class Solution {

    static int row;
    static int col;
    static char[][] map;
    static boolean canStop;
    static final int RIGHT = 0;
    static final int LEFT = 1;
    static final int UP = 2;
    static final int DOWN = 3;
    static int[][] move = {{0,1}, {0,-1}, {-1,0}, {1,0}};
    static boolean[][][][] visited;

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            row = sc.nextInt();
            col = sc.nextInt();
            map = new char[row][col];
            visited = new boolean[row][col][4][16];
            canStop = false;
            boolean has = false;

            for (int i = 0; i < row; i++) {
                String str = sc.next();
                for (int j = 0; j < col; j++) {
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == '@') {
                        has = true;
                    }
                }
            }
            if (!has) {
                System.out.println("#" +test_case + " NO");
                continue;
            }

            solve(0,0,0,0);
            if (canStop) {
                System.out.println("#" + test_case + " YES");
            } else {
                System.out.println("#" + test_case + " NO");
            }

        }
    }

    public static void solve(int x, int y, int dir, int memory) {

        if (canStop) {
            return;
        }
        if (visited[x][y][dir][memory]) {
            return;
        }
        visited[x][y][dir][memory] = true;

        char cmd = map[x][y];

        if (cmd == '@') {
            canStop = true;
            return;
        }

        if (cmd == '?') {

            for (int i = 0; i < 4; i++) {
                visited[x][y][i][memory] = true;
                int nextX = next(x + move[i][0], row);
                int nextY = next(y + move[i][1], col);

                solve(nextX, nextY, i, memory);

                if (canStop) {
                    return;
                }
            }

        } else {
            if ('0' <= cmd && cmd <= '9') {
                memory = cmd - '0';
            }
            switch(cmd) {
                case '<':
                    dir = LEFT;
                    break;
                case '>':
                    dir = RIGHT;
                    break;
                case '^':
                    dir = UP;
                    break;
                case 'v':
                    dir = DOWN;
                    break;
                case '_':
                    if (memory ==0) {
                        dir = RIGHT;
                    } else {
                        dir = LEFT;
                    }
                    break;
                case '|':
                    if (memory == 0) {
                        dir = DOWN;
                    } else {
                        dir = UP;
                    }
                    break;
                case '+':
                    if (memory == 15) {
                        memory = 0;
                    } else {
                        memory++;
                    }
                    break;
                case '-':
                    if (memory == 0) {
                        memory = 15;
                    } else {
                        memory--;
                    }
                    break;
            }
            int nextX = next(x + move[dir][0], row);
            int nextY = next(y + move[dir][1], col);

            solve(nextX, nextY, dir, memory);
        }

    }
    static int next(int x, int r) {
        if (x < 0) {
            return r -1;
        } else if(x >= r) {
            return 0;
        } else {
            return x;
        }
    }

}
