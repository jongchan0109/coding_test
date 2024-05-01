package programmers.javasolve.level3.puzzle;

import java.util.*;

class Solution {

    static int row;
    static int col;
    static boolean[][] visited;
    static int[][] move = {{0,1}, {0,-1}, {1,0}, {-1,0}};

    static class Puzzle {
        int size;
        List<int[]> point;

        Puzzle() {
            this.size = 0;
            this.point = new ArrayList<>();
        }
        void addPoint(int i, int j) {
            this.point.add(new int[] {i,j});
            this.size++;
        }

        boolean match(Puzzle other) {
            if (other.size != this.size) {
                return false;
            }
            int[][] thisArr = shape(this.point);
            int[][] otherArr = shape(other.point);

            for (int i = 0; i < 4; i++) {
                if (Arrays.deepEquals(thisArr, otherArr)) {
                    return true;
                }
                otherArr = rotate(otherArr);
            }
            return false;
        }

        private int[][] rotate(int[][] arr) {
            int[][] newArr = new int[arr[0].length][arr.length];

            for (int i = 0; i < newArr.length; i++) {
                for (int j = 0; j < newArr[0].length; j++) {
                    newArr[i][j] = arr[arr.length-1-j][i];
                }
            }
            return newArr;
        }

        private int[][] shape(List<int[]> point) {

            int minRow = 51;
            int minCol = 51;
            int maxRow = 0;
            int maxCol = 0;

            for (int[] p: point) {

                if (minRow > p[0]) {
                    minRow = p[0];
                }
                if (maxRow < p[0]) {
                    maxRow = p[0];
                }

                if (minCol > p[1]) {
                    minCol = p[1];
                }
                if (maxCol < p[1]) {
                    maxCol = p[1];
                }
            }

            for (int[] p: point) {
                p[0] -= minRow;
                p[1] -= minCol;
            }
            maxRow++;
            maxCol++;
            maxRow -= minRow;
            maxCol -= minCol;

            int[][] arr = new int[maxRow][maxCol];

            for (int[] p: point) {
                arr[p[0]][p[1]] = 1;
            }
            return arr;
        }


    }

    public int solution(int[][] game_board, int[][] table) {

        row = game_board.length;
        col = game_board[0].length;
        visited = new boolean[row][col];

        List<Puzzle> boards = createList(game_board, 0);
        visited = new boolean[row][col];
        List<Puzzle> tables = createList(table, 1);

        return calculate(boards, tables);


    }

    public int calculate(List<Puzzle> boards, List<Puzzle> tables) {

        int sum = 0;
        boolean[] tab = new boolean[tables.size()];

        for (Puzzle board : boards) {
            for (int j = 0; j < tables.size(); j++) {
                if (board.match(tables.get(j)) && !tab[j]) {
                    tab[j] = true;
                    sum += board.size;
                    break;
                }
            }
        }
        return sum;
    }


    public List<Puzzle> createList(int[][] board, int sign) {
        List<Puzzle> boards = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == sign && !visited[i][j]) {
                    boards.add(BFS(board, i, j, sign));
                }
            }
        }
        return boards;
    }

    public Puzzle BFS(int[][] board, int i , int j, int sign) {
        Puzzle puzzle = new Puzzle();
        puzzle.addPoint(i,j);
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i,j});
        visited[i][j] = true;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int m = 0; m < 4; m++) {
                int nextI = current[0] + move[m][0];
                int nextJ = current[1] + move[m][1];

                if (range(nextI, nextJ) && !visited[nextI][nextJ] && board[nextI][nextJ] == sign) {
                    queue.offer(new int[]{nextI, nextJ});
                    visited[nextI][nextJ] = true;
                    puzzle.addPoint(nextI, nextJ);
                }
            }

        }
        return puzzle;
    }

    public boolean range(int i, int j) {
        return 0 <= i && i < row && 0 <= j && j < col;
    }

}



public class Main {
    public static void main(String[] args) {
        int[][] gameBoard = {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}};
        int[][] tables = {{1,0,0,1,1,0}, {1,0,1,0,1,0}, {0,1,1,0,1,1},{0,0,1,0,0,0}, {1,1,0,1,1,0},{0,1,0,0,0,0}};
        int result = new Solution().solution(gameBoard, tables);
        System.out.println("result = " + result);
    }
}
