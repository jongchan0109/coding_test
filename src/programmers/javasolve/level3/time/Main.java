package programmers.javasolve.level3.time;

class Solution {

    static int row;
    static int[][] copy;

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};


    public int solution(int[][] clockHands) {
        int answer = 0;
        row = clockHands.length;
        copy = new int[row+2][row+2];

        for (int i = 1; i <= row ; i++) {
            if (row >= 0) System.arraycopy(clockHands[i - 1], 0, copy[i], 1, row);
        }

        while (true) {
            int max = 0;
            int maxRow = 0;
            int maxCol = 0;

            for (int i = 1; i <= row ; i++) {
                for (int j = 1; j <= row; j++) {
                    if (adjacent(i,j) > max) {
                        max = adjacent(i,j);
                        maxRow = i;
                        maxCol = j;
                    }
                }
            }

            if (max == 0) {
                break;
            }

            update(maxRow,maxCol);
            answer++;
        }

        return answer;
    }

    int adjacent(int i , int j) {
        int sum = copy[i][j] % 4;

        for (int t = 0; t < 4; t++) {
            int nextI = i + dx[t];
            int nextJ = j + dy[t];
            sum += copy[nextI][nextJ] % 4;
        }
        return sum;
    }

    void update(int i, int j) {

        copy[i][j] = (copy[i][j] + 1) % 4;

        for (int t = 0; t < 4; t++) {
            int nextI = i + dx[t];
            int nextJ = j + dy[t];
            if (range(nextI, nextJ)) {
                copy[nextI][nextJ] = (copy[nextI][nextJ] + 1) % 4;
            }
        }

    }

    public boolean range(int i, int j) {
        return i != 0 && i != row + 1 && j != 0 && j != row + 1;
    }

}
public class Main {
    public static void main(String[] args) {
        int[][] clockHands = {{0, 3, 3, 0}, {3, 2, 2, 3}, {0, 3, 2, 0}, {0, 3, 3, 3}};
        int result = new Solution().solution(clockHands);
        System.out.println("result = " + result);
    }
}
