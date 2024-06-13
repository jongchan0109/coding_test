package boj.gold.b1525;

import java.util.*;

public class Main {

    public static class Board {
        int count;
        int[][] board;

        public Board(int count, int[][] board) {
            this.count = count;
            this.board = board;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Board board1 = (Board) o;
            return Objects.deepEquals(board, board1.board);
        }

        @Override
        public int hashCode() {
            return Objects.hash(Arrays.deepHashCode(board));
        }
    }

    static int[][] arr;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        arr = new int[3][3];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        Set<Board> set = new HashSet<>();
        Queue<Board> queue = new LinkedList<>();
        set.add(new Board(0, deepCopy(arr)));
        queue.add(new Board(0, deepCopy(arr)));

        while (!queue.isEmpty()) {
            Board board = queue.poll();

            if (Arrays.deepEquals(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}, board.board)) {
                return board.count;
            }

            int[][] up = move(board.board, "up");
            if (up != null) {
                Board upBoard = new Board(board.count + 1, up);
                if (!set.contains(upBoard)) {
                    set.add(upBoard);
                    queue.add(upBoard);
                }
            }

            int[][] down = move(board.board, "down");
            if (down != null) {
                Board downBoard = new Board(board.count + 1, down);
                if (!set.contains(downBoard)) {
                    set.add(downBoard);
                    queue.add(downBoard);
                }
            }

            int[][] left = move(board.board, "left");
            if (left != null) {
                Board leftBoard = new Board(board.count + 1, left);
                if (!set.contains(leftBoard)) {
                    set.add(leftBoard);
                    queue.add(leftBoard);
                }
            }

            int[][] right = move(board.board, "right");
            if (right != null) {
                Board rightBoard = new Board(board.count + 1, right);
                if (!set.contains(rightBoard)) {
                    set.add(rightBoard);
                    queue.add(rightBoard);
                }
            }
        }
        return -1;
    }

    private static int[][] deepCopy(int[][] board) {
        int[][] copy = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            copy[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return copy;
    }

    private static int[][] move(int[][] board, String direction) {
        int[] zero = findZero(board);
        int x = zero[0];
        int y = zero[1];
        int newX = x, newY = y;

        switch (direction) {
            case "up":
                newX = x - 1;
                break;
            case "down":
                newX = x + 1;
                break;
            case "left":
                newY = y - 1;
                break;
            case "right":
                newY = y + 1;
                break;
        }

        if (newX < 0 || newX >= 3 || newY < 0 || newY >= 3) {
            return null;
        }

        int[][] newBoard = deepCopy(board);
        newBoard[x][y] = newBoard[newX][newY];
        newBoard[newX][newY] = 0;

        return newBoard;
    }

    private static int[] findZero(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }
}
