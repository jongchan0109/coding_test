package javasolve.level3.wagonmove;

class Solution {

    static final int MAX = 1000000000;

    static int row;
    static int col;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int[][] map;

    static boolean redEnd;
    static boolean blueEnd;

    static boolean[][][] visited;

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isEqual(Point other) {
            return this.x == other.x && this.y == other.y;
        }
    }

    public int solution(int[][] maze) {
        map = maze.clone();
        row = map.length;
        col = map[0].length;
        visited = new boolean[row][col][2];
        Point startRed = null;
        Point startBlue = null;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (maze[i][j] == 1) {
                    startRed = new Point(i, j);
                    visited[i][j][0] = true;
                } else if (maze[i][j] == 2) {
                    startBlue = new Point(i, j);
                    visited[i][j][1] = true;
                }
            }
        }

        int answer = backtracking(startRed, startBlue, 0);

        if (answer == MAX) {
            return 0;
        } else {
            return answer;
        }
    }

    public int backtracking(Point red, Point blue, int count) {
        if (redEnd & blueEnd) {
            return count;
        }
        int answer = MAX;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Point nextRed = redEnd ? red : getNext(red, i);
                Point nextBlue = blueEnd ? blue : getNext(blue, j);

                if (!isPossible(red, nextRed, blue, nextBlue)) {
                    continue;
                }
                visited[nextRed.x][nextRed.y][0] = true;
                visited[nextBlue.x][nextBlue.y][1] = true;

                if (map[nextRed.x][nextRed.y] == 3) {
                    redEnd = true;
                }
                if (map[nextBlue.x][nextBlue.y] == 4) {
                    blueEnd = true;
                }

                answer = Math.min(answer, backtracking(nextRed, nextBlue, count + 1));

                redEnd = false;
                blueEnd = false;

                visited[nextRed.x][nextRed.y][0] = false;
                visited[nextBlue.x][nextBlue.y][1] = false;
            }
        }
        return answer;
    }

    public Point getNext(Point p, int i) {
        return new Point(p.x + dx[i], p.y + dy[i]);
    }


    public boolean isPossible(Point currentRed, Point nextRed, Point currentBlue, Point nextBlue) {
        // 범위에 들지 않는 경우 false 리턴
        if (range(nextRed) || range(nextBlue)) {
            return false;
        }

        if (map[nextRed.x][nextRed.y] == 5 || map[nextBlue.x][nextBlue.y] == 5) {
            return false;
        }

        // 스왑한 경우 false, 아닌경우 true 리턴
        if (currentRed.isEqual(nextBlue) && currentBlue.isEqual(nextRed)) {
            return false;
        }

        if (nextBlue.isEqual(nextRed)) {
            return false;
        }

        return (!(!redEnd & visited[nextRed.x][nextRed.y][0])) && !(!blueEnd & visited[nextBlue.x][nextBlue.y][1]);
    }

    public boolean range(Point point) {
        return 0 > point.x || point.x >= row || 0 > point.y || point.y >= col;
    }

}

public class Main {
    public static void main(String[] args) {
        int[][] maze = {{1,4}, {0,0}, {2,3}};

        int result = new Solution().solution(maze);
        System.out.println("result = " + result);
    }
}
