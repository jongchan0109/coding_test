package boj.gold.b13460;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

    static char[][] map;
    static int result;

    static int n;
    static int m;

    static int endX;
    static int endY;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static boolean[][][][] visited; // 좌표(레드 블루)

    public static class Marble {
        int redX;
        int redY;
        int blueX;
        int blueY;
        int count;

        public Marble(int redX, int redY, int blueX, int blueY, int count) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.count = count;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        result = Integer.MAX_VALUE;

        map = new char[n][m];
        visited = new  boolean[n][m][n][m];

        Marble marble = new Marble(0,0,0,0,0);

        for (int i = 0; i < n; i++) {
            String str = sc.next();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'R') {
                    marble.redX = i;
                    marble.redY = j;
                } else if(map[i][j] == 'B') {
                    marble.blueX = i;
                    marble.blueY = j;
                } else if(map[i][j] == 'O') {
                    endX = i;
                    endY = j;
                }
            }
        }

        int result = BFS(marble);

        System.out.println(result);

    }

    private static int BFS(Marble marble) {

        Queue<Marble> queue = new LinkedList<>();
        queue.add(marble);
        visited[marble.redX][marble.redY][marble.blueX][marble.blueY] = true;

        while (!queue.isEmpty()) {
            Marble current = queue.poll();

            if (current.count >= 10) {
                continue;
            }

            for (int i = 0; i < 4; i++) {



                int[] red = {current.redX, current.redY};
                int[] blue = {current.blueX, current.blueY};

                boolean redHole = next(red, i);
                boolean blueHole = next(blue, i);

                if (blueHole) {
                    continue;
                }
                else if (redHole) {
                    return current.count + 1;
                }
                int redX = red[0];
                int redY = red[1];
                int blueX = blue[0];
                int blueY = blue[1];

                if (redX == blueX && redY == blueY) {

                    switch(i) {
                        case 0:
                            if (current.redY > current.blueY) {
                                redY++;
                            } else {
                                blueY++;
                            }
                            break;
                        case 1:
                            if (current.redY > current.blueY) {
                                blueY--;
                            } else {
                                redY--;
                            }
                            break;
                        case 2:
                            if (current.redX > current.blueX) {
                                redX++;
                            } else {
                                blueX++;
                            }
                            break;
                        case 3:
                            if (current.redX > current.blueX) {
                                blueX--;
                            } else {
                                redX--;
                            }
                            break;
                    }
                }

                if (visited[redX][redY][blueX][blueY]) {
                    continue;
                }
                visited[redX][redY][blueX][blueY] = true;

                queue.offer(new Marble(redX, redY, blueX, blueY, current.count + 1));

            }

        }
        return -1;
    }

    private static boolean next(int[] marble, int i) {

        while (true) {
            int nextX = marble[0] + dx[i];
            int nextY = marble[1] + dy[i];

            if (map[nextX][nextY] == '#') {
                break;
            }

            if (map[nextX][nextY] == 'O') {
                return true;
            }
            marble[0] = nextX;
            marble[1] = nextY;
        }

        return false;
    }

}

