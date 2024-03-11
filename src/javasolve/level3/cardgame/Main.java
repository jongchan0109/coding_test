package javasolve.level3.cardgame;

import java.util.*;

class Solution {

    static int min = Integer.MAX_VALUE;
    static int[][] map;
    static int[][] move = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    static Point[][] cardPos = new Point[7][2];
    static boolean[] exist = new boolean[7];
    static int cardCount = 0;


    static class Point {
        int x;
        int y;
        int count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }


    public int solution(int[][] board, int r, int c) {
        map = board;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j] == 0) {
                    continue;
                }

                if (exist[map[i][j]]) {
                    cardPos[map[i][j]][1] = new Point(i,j,0);
                } else {
                    cardCount++;
                    exist[map[i][j]] = true;
                    cardPos[map[i][j]][0] = new Point(i,j,0);
                }

            }
        }

        DFS(0,0,r,c);
        return min;
    }

    public void DFS(int ind, int sum, int r, int c) {
        if (ind == cardCount) {
            min = Math.min(sum, min);
            return;
        }

        for (int i = 1; i <= cardCount; i++) {
            if (!exist[i]) {
                continue;
            }

            exist[i] = false;
            int dis1 = BFS(r,c,cardPos[i][0].x, cardPos[i][0].y) + BFS(cardPos[i][0].x, cardPos[i][0].y, cardPos[i][1].x, cardPos[i][1].y) + 2;
            int dis2 = BFS(r,c,cardPos[i][1].x, cardPos[i][1].y) + BFS(cardPos[i][1].x, cardPos[i][1].y, cardPos[i][0].x, cardPos[i][0].y) + 2;

            map[cardPos[i][0].x][cardPos[i][0].y] = 0;
            map[cardPos[i][1].x][cardPos[i][1].y] = 0;

            if (dis1 < dis2) {
                DFS(ind + 1, sum + dis1, cardPos[i][1].x, cardPos[i][1].y);
            } else {
                DFS(ind + 1, sum + dis2, cardPos[i][0].x, cardPos[i][0].y);
            }

            exist[i] = true;
            map[cardPos[i][0].x][cardPos[i][0].y] = i;
            map[cardPos[i][1].x][cardPos[i][1].y] = i;

        }

    }


    public int BFS(int startX, int startY, int endX, int endY) {

        boolean[][] visited = new boolean[4][4];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startX, startY, 0));

        while(!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.x == endX && current.y == endY) {
                return current.count;
            }

            //1. Ctrl 없이 이동하는 경우

            for (int i = 0; i < 4; i++) {
                int nx = current.x + move[i][0];
                int ny = current.y + move[i][1];

                if (range(nx,ny) && !visited[nx][ny]) {
                    queue.add(new Point(nx, ny, current.count + 1));
                }
            }


            for (int i = 0; i < 4; i++) {
                int X = current.x;
                int Y = current.y;
                while(true) {
                    X += move[i][0];
                    Y += move[i][1];

                    if (!range(X,Y)) {
                        X -= move[i][0];
                        Y -= move[i][1];
                        break;
                    }

                    if (map[X][Y] != 0) {
                        break;
                    }
                }

                if (visited[X][Y]) {
                    continue;
                }
                visited[X][Y] = true;
                queue.add(new Point(X, Y, current.count + 1));
            }
        }
        return 0;
    }

    boolean range(int x, int y) {
        return 0<= x && x < 4 && 0 <= y && y < 4;
    }

}

public class Main {
    public static void main(String[] args) {
        int[][] board = {{1,0,0,3}, {2,0,0,0}, {0,0,0,2}, {3,0,1,0}};
        int r = 1;
        int c = 0;
        int result = new Solution().solution(board,r,c);
        System.out.println("result = " + result);
    }
}
