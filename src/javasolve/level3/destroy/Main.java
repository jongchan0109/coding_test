package javasolve.level3.destroy;

class Solution {
    public int solution(int[][] board, int[][] skill) {

        int[][] sum = new int[board.length + 1][board[0].length + 1];

        for (int[] s : skill) {

            if (s[0] == 1) {
                s[5] *= -1;
            }

            sum[s[1]][s[2]] += s[5];
            sum[s[3] + 1][s[4] + 1] += s[5];
            sum[s[1]][s[4] + 1] -= s[5];
            sum[s[3] + 1][s[2]] -= s[5];

        }

        //오른쪽으로 누적합
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sum[i][j + 1] = sum[i][j + 1] + sum[i][j];
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sum[i + 1][j] = sum[i][j] + sum[i + 1][j];
            }
        }


        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] += sum[i][j];
            }
        }

        return count(board);
    }


    int count(int[][] board) {
        int sum = 0;

        for (int[] row : board) {
            for (int col : row) {
                if (col >= 1) {
                    sum++;
                }
            }
        }
        return sum;
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] board = {{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}};
        int[][] skill = {{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}};
        int result = new Solution().solution(board, skill);
        System.out.println("result = " + result);
    }
}
