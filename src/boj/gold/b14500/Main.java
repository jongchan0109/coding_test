package boj.gold.b14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * start: 17:50
 * 방법 1. 4개의 좌표를 선택해서, 그들이 연결되어 있는지 확인
 * -> 연결되어 있으면 값 계산 O(NM C 4) 매우 비 효율적 방법
 * 2. n * m개의 점 중 한 점을 선택, 그 후 재귀적으로 크기가 4가 될 때 까지 인접 지역 탐색한 후 값 계산
 * -> 방법 1보다는 효율적, O(NM)??
 * -> 중복 처리할 방법이 있나??? 없음.
 * 방법 2로 진행 필요 변수
 * int[][] board, boolean[][] visited int[][] delta
 * int[][][] forkYou
 * int n, m, max
 * 필요 메서드 search(int row, int col, int depth, int sum)
 * -> 4방향으로 탐색, 깊이 4까지, 중복 체크
 * isRange(int row, int col) -> 범위에 드는지 확인
 * another(int row, int col) -> 한번 빠꾸를 치는 방식
 */

public class Main {

    static int n;
    static int m;
    static int max = 0;
    static int[][] board;
    static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visited;

    static int[][][] forkYou = {{{0, 1}, {0, 2}, {-1, 1}}, {{1, 0}, {2, 0}, {1, -1}},
        {{0, 1}, {0, 2}, {1, 1}}, {{1, 0}, {2, 0}, {1, -1}}}; // 정석


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                search(i, j, 0, board[i][j]); // 뻐큐 모양만 따로 만드는 게 편할 듯?
                visited[i][j] = false;
                another(i, j);

            }
        }
        System.out.println(max);
    }

    private static void search(int row, int col, int depth, int sum) {

        if (depth == 3) {
            max = Math.max(max, sum);
            return;
        }

        for (int[] d : delta) {
            int nextRow = row + d[0];
            int nextCol = col + d[1];

            if (isRange(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                visited[nextRow][nextCol] = true;
                search(nextRow, nextCol, depth + 1, sum + board[nextRow][nextCol]);
                visited[nextRow][nextCol] = false;
            }
        }
    }

    private static void another(int row, int col) {

        // 뻐큐 모양은 4가지가 있음
        for (int[][] fork : forkYou) {
            int sum = board[row][col];
            boolean flag = true;
            for (int[] d: fork) {
                int nextRow = row + d[0];
                int nextCol = col + d[1];

                if (isRange(nextRow, nextCol)) {
                    sum += board[nextRow][nextCol];
                } else {
                    flag = false;
                }
            }
            if (flag) {
                max = Math.max(max, sum);
            }

        }


    }

    private static boolean isRange(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < m;
    }

}
