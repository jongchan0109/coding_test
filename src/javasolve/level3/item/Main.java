package javasolve.level3.item;

import java.util.*;

class Solution {

    static boolean[][] map;
    static int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static class Rectangle {
        int sX;
        int sY;
        int eX;
        int eY;

        public Rectangle(int sX, int sY, int eX, int eY) {
            this.sX = sX;
            this.sY = sY;
            this.eX = eX;
            this.eY = eY;
        }

        public boolean include(int x, int y) {
            return sX < x && x < eX && sY < y && y < eY;
        }
    }

    static class Point {
        int x;
        int y;
        int distance;

        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }


    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        map = new boolean[101][101];
        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;
        List<Rectangle> rectangles = new ArrayList<>();

        for (int[] p : rectangle) {
            for (int i = 0; i < 4; i++) {
                p[i] *= 2;
            }
            rectangles.add(new Rectangle(p[0], p[1], p[2], p[3]));
        }

        for (int[] p : rectangle) {

            for (int x = p[0]; x <= p[2]; x++) {
                for (int y = p[1]; y <= p[3]; y++) {
                    boolean flag = true;
                    for (Rectangle rect : rectangles) {
                        if (rect.include(x,y)) {
                            flag = false;
                        }
                    }
                    map[x][y] = (x == p[0] || x == p[2] || y == p[1] || y == p[3]) && flag;
                }
            }
        }

        return BFS(characterX, characterY, itemX, itemY);
    }

    int BFS(int startX, int startY, int endX, int endY) {

        boolean[][] visited = new boolean[101][101];

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(startX, startY, 0));
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.x == endX && current.y == endY) {
                return current.distance / 2;
            }


            for (int d = 0; d < 4; d++) {
                int nextX = current.x + move[d][0];
                int nextY = current.y + move[d][1];
                if (range(nextX, nextY) && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    queue.offer(new Point(nextX, nextY, current.distance + 1));
                }

            }

        }
        return -1;
    }

    boolean range(int x, int y) {
        if (x < 1 || x > 100 || y < 1 || y > 100) {
            return false;
        }

        return map[x][y];
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] rectangle = {{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}};
        int cX = 1;
        int cY = 3;
        int iX = 7;
        int iY = 8;
        int result = new Solution().solution(rectangle, cX, cY, iX, iY);
        System.out.println("result = " + result);
    }
}