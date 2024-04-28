package javasolve.level3.littlefriends;

import java.util.*;

class Solution {

    static char[][] arr;
    static StringBuilder sb;

    public String solution(int m, int n, String[] board) {
        arr = new char[m][n];
        sb = new StringBuilder();
        Map<Character, ArrayList<int[]>> map = new HashMap<>();

        for (int i = 0; i < 26; i++) {
            map.put((char) ('A' + i), new ArrayList<>());
        }


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = board[i].charAt(j);

                if (arr[i][j] != '*' && arr[i][j] != '.') {
                    map.get(arr[i][j]).add(new int[]{i, j});
                }

            }
        }

        for (int i = 0; i < 26; i++) {
            if (map.get((char)('A' + i)).isEmpty()) {
                map.remove((char)('A' + i));
            }
        }


        while (!map.isEmpty()) {
            boolean flag = false;

            for (int i = 0; i < 26; i++) {
                char ch = (char) ('A' + i);

                if (map.get(ch) == null) {
                    continue;
                }

                int[] start = map.get(ch).get(0);
                int[] end = map.get(ch).get(1);

                if (reach(start, end, ch)) {


                    map.remove(ch);
                    arr[start[0]][start[1]] = '.';
                    arr[end[0]][end[1]] = '.';
                    flag = true;
                    sb.append(ch);
                    break;
                }

            }
            if (!flag) {
                return "IMPOSSIBLE";
            }

        }
        return sb.toString();
    }

    private boolean reach(int[] start, int[] end, char ch) {

        int startX = start[0]; // 항상 endX보다 작음
        int startY = start[1];
        int endX = end[0];
        int endY = end[1];

        if (endY > startY) {
            // (startX, startY) -> (startX, endY) -> (endX, endY)
            if (lineColCheck(startY, endY, startX, ch) && lineRowCheck(startX, endX, endY, ch)) {
                return true;
            }
            // (startX, startY) -> (endX, startY) -> (endX, endY)
            return lineRowCheck(startX, endX, startY, ch) && lineColCheck(startY, endY, endX, ch);
        } else {

            if (ch == 'D') {
                System.out.println("startX = " + start[0] + " startY = " + start[1]);
                System.out.println("endX = " + end[0] + " endY = " + end[1]);
            }

            // (startX, startY) -> (startX, endY) -> (endX, endY)
            if (lineColCheck(endY, startY, endX, ch) && lineRowCheck(startX, endX, startY, ch)) {
                return true;
            }
            // (startX, startY) -> (endX, startY) -> (endX, endY)
            return (lineRowCheck(startX, endX, endY, ch)) && lineColCheck(endY, startY, startX, ch);

        }

    }

    private boolean lineRowCheck(int r1, int r2, int c, char ch) {

        for (int i = r1; i <= r2; i++) {
            if (arr[i][c] != '.' && arr[i][c] != ch) {
                return false;
            }
        }
        return true;
    }

    private boolean lineColCheck(int c1, int c2, int r, char ch) {
        for (int i = c1; i <= c2; i++) {
            if (arr[r][i] != '.' && arr[r][i] != ch) {
                return false;
            }
        }
        return true;
    }

}

public class Main {
    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        String[] board = {"DBA", "C*A", "CDB"};
        String answer = new Solution().solution(m,n,board);
        System.out.println("answer = " + answer);
    }
}
