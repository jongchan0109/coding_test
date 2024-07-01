package swea.d1.d1_1936;

import java.util.Scanner;

class Solution {
    static int A;
    static int B;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        A = scanner.nextInt();
        B = scanner.nextInt();
    }

    //A가 이기는 경우,
    //1. A가 가위, B가 보 A-B = -2
    //2. A가 바위, B가 가위 A-B = 1
    //3. A가 보, B가 바위 A-B = 1

    //A가 지는 경우
    //1. A가 가위, B가 바위 A-B = -1
    //2. A가 바위, B가 보 A-B = -1
    //3. A가 보, B가 가위 A-B = 2

    //비기는 경우
    // A-B = 0

    private static void solve() {

        if (A - B == -2 || A - B == 1) {
            System.out.println('A');
        } else {
            System.out.println('B');
        }
    }
}
