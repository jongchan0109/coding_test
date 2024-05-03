package swea.d3.d3_17642;

import java.util.Scanner;

class Solution
{
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)	 {
            System.out.print("#" + test_case);

            long a = sc.nextLong();
            long b = sc.nextLong();
            long dif = Math.abs(b - a);

            if (b - a <= 1 && b - a != 0) {
                System.out.println(" " + -1);
                continue;
            }

            System.out.println(" " + dif / 2);

        }
    }
}
