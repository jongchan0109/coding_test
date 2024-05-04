package swea.d3.d3_16910;


import java.util.Scanner;

class Solution
{
    public static void main(String[] args) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();


        for(int test_case = 1; test_case <= T; test_case++) {
            int r = sc.nextInt();

            System.out.println("#" + test_case +" " + solve(r));

        }
    }

    public static int solve(int r) {
        int result = 0;

        for (int i = -r; i <= r; i++ ) {
            for (int j = -r ; j <= r; j++ ) {
                if (i * i + j * j <= r* r) {
                    result++;
                }
            }
        }

        return result;
    }
}
