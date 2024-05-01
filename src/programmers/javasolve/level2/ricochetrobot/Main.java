package programmers.javasolve.level2.ricochetrobot;

import java.util.LinkedList;
import java.util.Queue;

class Solution {

    boolean[][] visited;
    String[] board;

    public boolean isVisit(int[] arr) {
        return !visited[arr[0]][arr[1]];
    }

    public int BFS(int[] start, int[] end) {
        Queue<int[]> queue = new LinkedList<>();
        visited[start[0]][start[1]] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            if (current[0] == end[0] && current[1] == end[1]) {
                return current[2];
            }

            int[] next = left(current);
            if (isVisit(next)) {
                visited[next[0]][next[1]] = true;
                queue.offer(next);
            }
            next = right(current);
            if (isVisit(next)) {
                visited[next[0]][next[1]] = true;
                queue.offer(next);
            }

            next = up(current);
            if (isVisit(next)) {
                visited[next[0]][next[1]] = true;
                queue.offer(next);
            }

            next = down(current);
            if (isVisit(next)) {
                visited[next[0]][next[1]] = true;
                queue.offer(next);
            }
        }
        return -1;
    }

    private int[] down(int[] current) {
        int currentI = current[0];
        int currentJ = current[1];

        for (; currentI < board.length ; currentI++) {
            if (board[currentI].charAt(currentJ) == 'D')
                break;
        }
        currentI--;
        return new int[]{currentI, currentJ, current[2]+1};
    }

    private int[] up(int[] current) {
        int currentI = current[0];
        int currentJ = current[1];

        for (; currentI >= 0; currentI--) {
            if (board[currentI].charAt(currentJ) == 'D')
                break;
        }
        currentI++;
        return new int[]{currentI, currentJ, current[2]+1};
    }

    private int[] right(int[] current) {
        int currentI = current[0];
        int currentJ = current[1];

        for (; currentJ < board[0].length(); currentJ++) {
            if (board[currentI].charAt(currentJ) == 'D')
                break;
        }
        currentJ--;
        return new int[]{currentI, currentJ, current[2]+1};
    }

    private int[] left(int[] current) {
        int currentI = current[0];
        int currentJ = current[1];

        for (; currentJ >= 0; currentJ--) {
            if (board[currentI].charAt(currentJ) == 'D')
                break;
        }
        currentJ++;
        return new int[]{currentI, currentJ, current[2]+1};
    }

    public int solution(String[] board) {
        visited = new boolean[board.length][board[0].length()];
        this.board = board;

        int[] start = {0, 0, 0};
        int[] end = {0, 0, 0};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                if (board[i].charAt(j) == 'G') {
                    end = new int[]{i, j, 0};
                } else if (board[i].charAt(j) == 'R') {
                    start = new int[]{i, j, 0};
                }
            }
        }
        return BFS(start, end);
    }
}


public class Main {
    public static void main(String[] args) {
        String[] board = {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};
        int result = new Solution().solution(board);

        System.out.println("result = " + result);
    }
}
