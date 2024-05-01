package programmers.javasolve.level3.lockandkey;

class Solution {

    static int[][] map;
    static int m;
    static int n;
    public boolean solution(int[][] key, int[][] lock) {
        n = lock.length;
        m = key.length;
        int mapSize = n + (m - 1) * 2;
        map = new int[mapSize][mapSize];

        for(int i=m-1; i<m+n-1 ; i++){
            if (m + n - 1 - (m - 1) >= 0)
                System.arraycopy(lock[i - (m - 1)], 0, map[i], m - 1, m + n - 1 - (m - 1));
        }

        for (int i = 0; i < 4; i++) {
            if (check(key)) {
                return true;
            }
            rotate(key);
        }
        return false;
    }

    private boolean check(int[][] key) {



        for (int i = 0; i < map.length - m + 1; i++) {
            for (int j = 0; j < map.length - m + 1; j++) {
                for (int k = 0; k < m; k++) {
                    for (int l = 0; l < m ;l++) {

                        map[i+k][j+l] += key[k][l];
                    }
                }

                boolean flag = true;

                for (int k = m -1; k < m + n - 1; k++) {
                    for (int l = m -1; l < m + n - 1; l++) {
                        if (map[k][l] != 1) {
                            flag = false;
                            break;
                        }
                    }
                }

                if (flag) {
                    return true;
                }

                for (int k = 0; k < m; k++) {
                    for (int l = 0; l < m ;l++) {
                        map[i+k][j+l] -= key[k][l];
                    }
                }
            }
        }
        return false;
    }

    private void rotate(int[][] key) {
        int[][] temp = new int[m][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m ; j++) {
                temp[i][j] = key[m-j-1][i];
            }
        }
        for (int i = 0; i < m ;i++) {
            System.arraycopy(temp[i], 0, key[i], 0, m);
        }
    }

}

public class Main {
    public static void main(String[] args) {
        int[][] key = {{0,0,0}, {1,0,0}, {0,1,1}};
        int[][] lock = {{1,1,1}, {1,1,0}, {1,0,1}};
        boolean result = new Solution().solution(key, lock);
        System.out.println("result = " + result);
    }
}
