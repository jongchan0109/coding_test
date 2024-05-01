package programmers.javasolve.level3.mazeescape;

class Solution {

    // 왼쪽으로 한칸 이동, 오른쪽으로 한칸 이동, 위쪽으로 한칸 이동, 아래쪽으로 한칸 이동
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static String[] command = {"d", "l", "r", "u"}; //d, l, r, u

    // 이동, 출구까지 거리 계산,

    public String solution(int n, int m, int x, int y, int r, int c, int k) {

        StringBuilder str = new StringBuilder();

        for (int i = 1; i <= k ; i++) {
            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];

                if (range(n,m,nx,ny)) {
                    if (escape(nx,ny,r,c,k - i)) {
                        x = nx;
                        y = ny;
                        str.append(command[j]);
                        break;
                    }
                }

            }
        }
        String result = str.toString();
        if (result.length() != k) {
            return "impossible";
        } else {
            return result;
        }
    }

    public boolean escape(int x, int y, int r, int c, int k) {
        int dis = distance(x, y, r, c);
        if (dis > k) {
            return false;
        }
        return (k - dis) % 2 != 1;
    }

    public int distance(int x, int y, int r, int c) {
        return Math.abs(x - r) + Math.abs(c - y);
    }


    public boolean range(int n, int m, int x, int y) {
        return 1 <= x && x <= n && 1 <= y && y <= m;
    }

}

public class Main {
    public static void main(String[] args) {
        int n = 3;
        int m = 4;
        int x = 2;
        int y = 3;
        int r = 3;
        int c = 1;
        int k = 5;

        String result = new Solution().solution(n, m, x, y, r, c, k);
        System.out.println("result = " + result);
    }
}
