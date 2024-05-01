package programmers.javasolve.level3.ballmove;

class Solution {

    public long solution(int n, int m, int x, int y, int[][] queries) {
        int startX = x;
        int endX = x;
        int startY = y;
        int endY = y;

        for (int i = queries.length - 1; i >= 0; i--) {
            int command = queries[i][0];
            int dx = queries[i][1];

            int move;

            if (command == 1 || command == 3) {
                move = -dx;
            } else {
                move = dx;
            }
            int[] result;

            if (command == 0 || command == 1) {

                result = move(startY, endY, move, m);
                startY = result[0];
                endY = result[1];
            } else {
                result = move(startX, endX,move, n);
                startX = result[0];
                endX = result[1];
            }

            if (result[0] == -1) {
                return 0;
            }
        }

        return (long) (endX - startX + 1) * (long) (endY - startY + 1);
    }

    int[] move(int start, int end, int move, int max) {
        int moveS = (start==0 && move > 0) ? 0 : start + move;
        int moveE = (end==max-1 && move < 0 ) ? max-1 : end + move;

        if ((0 > moveS || moveS >=max) && (0 > moveE || moveE >= max)) {
            return new int[]{-1, -1};
        } else if(0 > moveS || moveS >= max) {
            return new int[]{0,moveE};
        } else if (0 > moveE || moveE >= max){
            return new int[]{moveS, max - 1};
        }


        return new int[]{moveS, moveE};
    }
}



public class Main {
    public static void main(String[] args) {
        int n = 2;
        int m = 5;
        int x = 0;
        int y = 1;
        int[][] queries = {{3, 1}, {2, 2}, {1, 1}, {2, 3}, {0, 1}, {2, 1}};
        long result = new Solution().solution(n, m, x, y, queries);
        System.out.println("result = " + result);
    }
}
