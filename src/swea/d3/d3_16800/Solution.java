package swea.d3.d3_16800;

import java.util.Scanner;

class Solution
{
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            long num = sc.nextLong();

            System.out.println("#" + test_case + " " + (calculate(num) - 2));
        }
    }


    public static  long calculate(long num) {
        long result = num + 1;

        for (int i = 1; i <= Math.sqrt(num); i++ ) {
            if (num % i == 0) {
                long opposite = num / i;

                result = Math.min(result, i + opposite);
            }
        }
        return result;

    }

}