package boj.gold.b17825;

import java.util.Scanner;

public class Main {

    static int[][] map;
    static int max = 0;
    static int[] arr;

    public static class Game {
        int[][] point;

        public Game() {
            point = new int[4][2]; // 1~4번째 말의 좌표
        }

        public Game(Game other) {
            point = new int[4][2];
            for (int i = 0; i < 4; i++) {
                point[i] = other.point[i].clone();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        init();
        arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = scanner.nextInt();
        }

        DFS(0, 0, new Game());

        System.out.println(max);
    }

    public static void DFS(int depth, int sum, Game game) {
        if (depth == 10) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            Game newGame = new Game(game); // 복사된 게임 상태 사용
            int[] current = newGame.point[i];

            int[] next = current.clone();
            next[0] += arr[depth];

            // 파란색 경로로의 분기
            if (next[1] == 0 && next[0] == 5) {
                next[1] = 1;
                next[0] = 0;
            } else if (next[1] == 0 && next[0] == 10) {
                next[1] = 2;
                next[0] = 0;
            } else if (next[1] == 0 && next[0] == 15) {
                next[1] = 3;
                next[0] = 0;
            }

            if (next[1] == 2) {
                if (next[0] >= 3) {
                    next[1] = 1;
                    next[0] = next[0] + 1;
                }
            } else if (next[1] == 3) {
                if (next[0] >= 4) {
                    next[1] = 1;
                }
            }


            if (!canMove(i, next, newGame)) {
                continue;
            }

            newGame.point[i] = next;

            DFS(depth + 1, sum + map[next[0]][next[1]], newGame);
        }
    }

    private static boolean canMove(int i, int[] next, Game game) {
        int[][] point = game.point;

        for (int j = 0; j < 4; j++) {
            if (j == i) continue;
            if (next[0] == point[j][0] && next[1] == point[j][1] && map[next[0]][next[1]] != 0) return false;

            for (int k = 1; k <= 3; k++) {
                if (next[0] == 0 && next[1] == k && point[j][0] == k * 5 && point[j][1] == 0) {
                    return false;
                }

                if (point[j][0] == 0 && point[j][1] == k && next[0] == k * 5 && next[1] == 0) {
                    return false;
                }
            }

            if (map[next[0]][next[1]] == 40 && map[point[j][0]][point[j][1]] == 40) {
                return false;
            }

        }


        return true;
    }

    public static void init() {
        map = new int[50][4];

        for (int i = 0; i < 21; i++) { // 빨간색 경로
            map[i][0] = i * 2;
        }
        map[21][0] = 0; // 도착 칸

        // 1번째 파란색 경로
        map[0][1] = 10;
        map[1][1] = 13;
        map[2][1] = 16;
        map[3][1] = 19;
        map[4][1] = 25;
        map[5][1] = 30;
        map[6][1] = 35;
        map[7][1] = 40;
        map[8][1] = 0; // 도착 칸

        // 2번째 파란색 경로
        map[0][2] = 20;
        map[1][2] = 22;
        map[2][2] = 24;
        map[3][2] = 25;
        map[4][2] = 30;
        map[5][2] = 35;
        map[6][2] = 40;
        map[7][2] = 0; // 도착 칸

        // 3번째 파란색 경로
        map[0][3] = 30;
        map[1][3] = 28;
        map[2][3] = 27;
        map[3][3] = 26;
        map[4][3] = 25;
        map[5][3] = 30;
        map[6][3] = 35;
        map[7][3] = 40;
        map[8][3] = 0; // 도착 칸
    }
}
