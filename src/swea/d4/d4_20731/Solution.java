package swea.d4.d4_20731;

import java.util.Scanner;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();

            char[][] arr = new char[n][n];

            for (int i = 0; i < n; i++) {
                String row = sc.next();
                arr[i] = row.toCharArray();
            }

            System.out.println("#" + test_case +" " + isPossible(arr, n));

        }
    }

    static String isPossible(char[][] arr, int n) {
        for (int k = 0; k < 500; k++) {
            boolean flag = true; // 조건을 만족하는지 여부를 나타내는 플래그

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 각 칸의 값을 직접 계산하여 비교
                    char expected = (gcd(i + k + 1, j + k + 1) == 1) ? '1' : '?';

                    // 예상 값과 실제 값이 다른 경우 플래그를 false로 설정하고 반복문 종료
                    if (arr[i][j] != expected) {
                        flag = false;
                        break;
                    }
                }
                if (!flag) {
                    break;
                }
            }
            if (flag) {
                return "YES";
            }
        }
        // 모든 경우를 검사했는데도 조건을 만족하는 격자판이 없는 경우 "NO" 반환
        return "NO";
    }


    static int gcd(int n, int m) {
        if (m ==0 ) {
            return n;
        }
        return gcd(m, n %m);
    }

}
