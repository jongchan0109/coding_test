package swea.d3.d3_17319;

import java.util.Scanner;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            System.out.print("#" + test_case);

            int length = sc.nextInt();
            String str = sc.next();

            if ( length % 2 == 1) {
                System.out.println(" No" );
                continue;
            }

            if (solve(str, length)) {
                System.out.println(" Yes");
            } else {
                System.out.println(" No" );
            }

        }
    }

    public static boolean solve(String str, int len) {
        int start = 0;
        int mid = len / 2;

        while (mid < len) {

            if (str.charAt(start) != str.charAt(mid)) {
                return false;
            }

            start++;
            mid++;
        }
        return true;

    }
}
