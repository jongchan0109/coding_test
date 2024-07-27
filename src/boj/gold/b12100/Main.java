package boj.gold.b12100;

import java.util.Scanner;

public class Main {

    static int n;

    static int[][] startBoard;

    static int max;

    public static void main(String[] args) {
        input();
        max = 0;
        dfs(0, startBoard);
        System.out.println(max);
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        startBoard = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                startBoard[i][j] = scanner.nextInt();
            }
        }
    }


    private static void dfs(int depth, int[][] board) {
        if (depth == 5) {
            max = Math.max(max, getMax(board));
            return;
        }

        int[][] left = left(board, true);

        dfs(depth + 1, left);

        int[][] right = right(board, true);

        dfs(depth + 1, right);


        int[][] up = up(board, true);

        dfs(depth + 1, up);


        int[][] down = down(board, true);

        dfs(depth + 1, down);
    }

    private static int getMax(int[][] board) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, board[i][j]);
            }
        }
        return max;
    }

    static int[][] left(int[][] board, boolean flag) {
        int[][] clone = deepCopy(board);

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {

                int index = j;

                while (index >= 1 && clone[i][index - 1] == 0) {
                    clone[i][index - 1] = clone[i][index];
                    clone[i][index] = 0;
                    index--;
                }
            }
        }
        if (flag) {
            return colMerge(clone, false);
        }
        return clone;
    }

    static int[][] right(int[][] board, boolean flag) {
        int[][] clone = deepCopy(board);
        for (int i = 0; i < n; i++) {
            for (int j = n - 2; j >= 0; j--) {

                int index = j;

                while (index < n - 1 && clone[i][index + 1] == 0) {
                    clone[i][index + 1] = clone[i][index];
                    clone[i][index] = 0;
                    index++;
                }
            }

        }
        if (flag) {
            return colMerge(clone, true);
        }
        return clone;
    }

    static int[][] up(int[][] board, boolean flag) {
        int[][] clone = deepCopy(board);

        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                int index = i;

                while (index >= 1 && clone[index - 1][j] == 0) {
                    clone[index - 1][j] = clone[index][j];
                    clone[index][j] = 0;
                    index--;
                }
            }
        }
        if (flag) {
            return rowMerge(clone, false);
        }
        return clone;
    }

    static int[][] down(int[][] board, boolean flag) {
        int[][] clone = deepCopy(board);

        for (int j = 0; j < n; j++) {
            for (int i = n - 2; i >= 0; i--) {

                int index = i;

                while (index < n - 1 && clone[index + 1][j] == 0) {
                    clone[index + 1][j] = clone[index][j];
                    clone[index][j] = 0;
                    index++;
                }
            }
        }
        if (flag) {
            return rowMerge(clone, true);
        }
        return clone;
    }


    static int[][] colMerge(int[][] clone, boolean flag) {

        int[][] board = deepCopy(clone);

        if (!flag) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n - 1; j++) {

                    if (board[i][j] == 0) {
                        continue;
                    }

                    if (board[i][j] == board[i][j + 1]) {
                        board[i][j] = 2 * board[i][j];
                        board[i][j + 1] = 0;
                        j++;
                    }
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = n - 1; j > 0; j--) {
                    if (board[i][j] == 0) {
                        continue;
                    }
                    if (board[i][j] == board[i][j - 1]) {
                        board[i][j - 1] = 2 * board[i][j];
                        board[i][j] = 0;
                        j--;
                    }
                }
            }
        }

        if (!flag) {
            board = left(board, false);
        } else {
            board = right(board, false);
        }
        return board;
    }

    static int[][] rowMerge(int[][] clone, boolean flag) {


        int[][] board = deepCopy(clone);

        if (!flag) {
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < n - 1; i++) {
                    if (board[i][j] == 0) {
                        continue;
                    }
                    if (board[i][j] == board[i + 1][j]) {
                        board[i][j] = 2 * board[i][j];
                        board[i + 1][j] = 0;
                        i++;
                    }
                }
            }
        } else {
            for (int j = 0; j < n; j++) {
                for (int i = n - 1; i > 0; i--) {
                    if (board[i][j] == 0) {
                        continue;
                    }
                    if (board[i - 1][j] == board[i][j]) {
                        board[i - 1][j] = 2 * board[i][j];
                        board[i][j] = 0;
                        i--;
                    }
                }
            }
        }

        if (!flag) {
            board = up(board, false);
        } else {
            board = down(board, false);
        }

        return board;
    }

    static int[][] deepCopy(int[][] original) {
        int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = original[i].clone();
        }
        return result;
    }
}
