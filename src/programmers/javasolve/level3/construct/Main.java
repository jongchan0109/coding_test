package programmers.javasolve.level3.construct;

class Solution {

    static int[][][] map;
    static int N;

    public int[][] solution(int n, int[][] build_frame) {
        map = new int[n + 1][n + 1][2];
        N = n;
        int length = 0;


        for (int[] build : build_frame) {
            int x = build[0];
            int y = build[1];
            int a = build[2];
            int b = build[3];

            if (b == 0) {
                map[x][y][a] = 0;
                length--;
                boolean flag = true;

                if (a == 0) {//기둥 삭제
                    if (x - 1 >= 0 && map[x - 1][y + 1][1] == 1 && !canConstruct(x - 1, y + 1, 1)) flag = false;
                    if (x + 1 <= N && map[x][y + 1][1] == 1 && !canConstruct(x, y + 1, 1)) flag = false;
                    if (y + 2 <= N && map[x][y + 1][0] == 1 && !canConstruct(x, y + 1, 0)) flag = false;
                } else { //보 삭제
                    if (y + 1 <= N && map[x][y][0] == 1 && !canConstruct(x, y, 0)) flag = false;
                    if (y + 1 <= N && map[x + 1][y][0] == 1 && !canConstruct(x + 1, y, 0)) flag = false;
                    if (x - 1 >= 0 && map[x - 1][y][1] == 1 && !canConstruct(x - 1, y, 1)) flag = false;
                    if (x + 2 <= N && map[x + 1][y][1] == 1 && !canConstruct(x + 1, y, 1)) flag = false;
                }

                if (!flag) {
                    map[x][y][a] = 1;
                    length++;
                }

            } else {
                if (canConstruct(x, y, a)) {
                    map[x][y][a] = 1;
                    length++;
                }
            }

        }
        int[][] answer = new int[length][3];
        int count = 0;

        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                for (int a = 0; a < 2; a++) {
                    if (map[x][y][a] == 1) {
                        answer[count][0] = x;
                        answer[count][1] = y;
                        answer[count++][2] = a;
                    }
                }
            }
        }
        return answer;
    }


    public boolean canConstruct(int x, int y, int a) {

        if (a == 0) {
            if (y == 0) { //바닥에 설치하는 경우
                return true;
            }
            if (x - 1 >= 0 && map[x - 1][y][1] == 1) { //보의 왼쪽에 설치하는 경우
                return true;
            }
            if (x + 1 <= N && map[x][y][1] == 1) { //보의 오른쪽에 설치하는 경우
                return true;
            }

            //기둥 위에 설치하는 경우
            return y - 1 >= 0 && map[x][y - 1][0] == 1;
        } else {
            if (y - 1 >= 0 && map[x][y - 1][0] == 1) { //왼쪽 끝이 기둥
                return true;
            }
            if (y - 1 >= 0 && map[x + 1][y - 1][0] == 1) { //오른쪽 끝이 기둥
                return true;
            }
            //양쪽 끝이 다른 보
            return x - 1 >= 0 && x + 1 < N && map[x - 1][y][1] == 1 && map[x + 1][y][1] == 1;
        }
    }

}

public class Main {
    public static void main(String[] args) {
        int n = 5;
        int[][] build_frame = {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}};
        int[][] result = new Solution().solution(n,build_frame);
        for (int[] i : result) {
            for(int j: i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
