package javasolve.level3.coinreverse;

import java.util.*;

class Solution {

    static Map<Integer, Integer> row = new HashMap<>();
    static Map<Integer, Integer> col = new HashMap<>();
    int rowLen;
    int colLen;

    public int solution(int[][] beginning, int[][] target) {

        rowLen = beginning.length;
        colLen = beginning[0].length;

        boolean[] rRow = new boolean[rowLen];
        boolean[] rCol = new boolean[colLen];

        diff(beginning, target);

        for (int i = 0; i < 100; i++) {
            int maxR = maxRow();
            int maxC = maxCol();

            if (maxR == -1 && maxC == -1) {
                return i;
            }

            if (row.get(maxR) > col.get(maxC)) {
                if (rRow[maxR]) {
                    break;
                }
                reverseRow(maxR, beginning);
                rRow[maxR] = true;
            } else {
                if (rCol[maxC]) {
                    break;
                }
                reverseCol(maxC, beginning);
                rCol[maxC] = true;
            }
            diff(beginning, target);
        }

        return -1;
    }

    public void reverseRow(int x, int[][] beginning) {

        for (int i = 0; i < colLen; i++) {
            if (beginning[x][i] == 0) {
                beginning[x][i] = 1;
            } else {
                beginning[x][i] = 0;
            }
        }

    }

    public void reverseCol(int x, int[][] beginning) {
        for (int i = 0; i < rowLen; i++) {
            if (beginning[i][x] == 0) {
                beginning[i][x] = 1;
            } else {
                beginning[i][x] = 0;
            }
        }
    }

    public int maxRow() {
        return max(rowLen, row);
    }

    public int maxCol() {
        return max(colLen, col);
    }

    private int max(int len, Map<Integer, Integer> map) {
        int max = 0;
        int maxIdx = -1;

        for (int i = 0; i < len; i++) {

            if (map.get(i) > max) {
                maxIdx = i;
                max = map.get(i);
            }
        }

        return maxIdx;
    }


    public void diff(int[][] beginning, int[][] target) {

        row.clear();
        col.clear();

        for (int i = 0; i < rowLen; i++) {
            row.put(i, 0);
        }
        for (int i = 0; i < colLen; i++) {
            col.put(i, 0);
        }

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (beginning[i][j] != target[i][j]) {
                    row.put(i, row.get(i) + 1);
                    col.put(j, col.get(j) + 1);
                }
            }
        }

    }

}

public class Main {
    public static void main(String[] args) {
        int[][] beginning = {{0, 0, 1, 0, 0}, {1, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        int[][] target = {{0, 1, 0, 1, 1}, {0, 0, 0, 0, 0}, {1, 0, 0, 0, 0}, {1, 0, 0, 0, 0}, {1, 0, 0, 0, 0}};

        int result = new Solution().solution(beginning, target);
        System.out.println("result = " + result);
    }
}
