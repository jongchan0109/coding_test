package swea.d3.d3_20551;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int testCase = 1; testCase <= T; testCase++) {
            String str = sc.next();

            if (isPalindrome(str)) {
                System.out.println("NO");
                continue;
            }
            int len = str.length();
            String left;
            String right = str.substring((len+1)/2);

            if (len % 2 == 1) {
                left = str.substring(0, (len-1)/2);
            } else {
                left = str.substring(0, len/2);
            }
            if (isPalindrome(left) && isPalindrome(right)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

        }

    }

    private static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left <= right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
