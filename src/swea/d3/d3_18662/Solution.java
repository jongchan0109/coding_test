package swea.d3.d3_18662;

import java.util.Scanner;

class Solution
{
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++){
            System.out.print("#" + test_case);
            double result;

            double a = sc.nextDouble();
            double b = sc.nextDouble();
            double c = sc.nextDouble();


            // 총 3 가지 a에 연산, b에 연산, c에 연산

            //a에 연산 a = 0 b = 2 c = 1
            double cB = c - b;
            double bA = b - a;

            result = Math.abs(cB - bA) / 2;

            System.out.printf(" %.1f\n", result);

        }
    }
}
