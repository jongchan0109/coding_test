package swea.d2.d2_1961;

import java.util.Scanner;

class Solution {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();


        for (int t = 1; t <= T ; t++) {
            int n = sc.nextInt();
            int[][] arr = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }


            int[][] rotateOne = rotate(arr);
            int[][] rotateTwo = rotate(rotateOne);
            int[][] rotateThree = rotate(rotateTwo);

            System.out.println("#" + t);
            for (int i = 0; i < n; i++) {
                print(rotateOne, i);
                print(rotateTwo, i);
                print(rotateThree, i);
                System.out.println();
            }

        }

    }

    private static void print(int[][] arr, int row) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[row][i]);
        }
        System.out.print(" ");
    }

    private static int[][] rotate(int[][] arr) {

        int n = arr.length;
        int[][] rotate = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotate[i][j] = arr[n-1-j][i];
            }
        }
        return rotate;
    }

}
