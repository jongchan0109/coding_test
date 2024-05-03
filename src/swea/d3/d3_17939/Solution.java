package swea.d3.d3_17939;

import java.util.Scanner;


class Solution
{
    public static void main(String[] args) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            System.out.print("#" + test_case);

            String first = sc.next();
            String second = sc.next();

            if (first.equals(second)) {
                System.out.println(" " + first);
            } else {
                System.out.println(" " + 1);
            }

        }
    }
}
