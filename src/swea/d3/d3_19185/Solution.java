package swea.d3.d3_19185;

import java.util.Scanner;

public class Solution
{
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {

            int m = sc.nextInt();
            int n = sc.nextInt();

            String[] first = new String[m];
            String[] second = new String[n];

            for (int i = 0; i < m; i++) {
                first[i] = sc.next();
            }

            for (int i = 0; i < n; i++) {
                second[i] = sc.next();
            }

            int length = sc.nextInt();

            System.out.print("#" + test_case);

            for (int i = 0; i < length; i++) {
                int year = sc.nextInt();

                String firstWord = first[(year - 1) % m];
                String secondWord = second[(year - 1) % n];

                System.out.print(" " + firstWord + secondWord);

            }
            System.out.println();

        }
    }
}