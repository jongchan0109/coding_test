package swea.d3.d3_19003;

import java.util.Scanner;

class Solution {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();


        for (int test_case = 1; test_case <= T; test_case++) {
            System.out.print("#" + test_case);

            int n = sc.nextInt();
            int m = sc.nextInt();
            String[] arr = new String[n];

            for (int i = 0; i < n; i++) {
                arr[i] = sc.next();
            }

            int reverseCount = 0;
            boolean palindrome = false;

            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (reverse(arr[i], arr[j], m)) {
                        reverseCount++;
                    }
                }
            }
            int result = m * reverseCount * 2;

            for (int i = 0; i < n; i++) {
                if (isPalindrome(arr[i], m)) {
                    boolean flag = true;
                    for (int j = i + 1; j < n; j++) {
                        if (reverse(arr[i], arr[j], m)) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        palindrome = true;
                    }
                }

            }
            if (palindrome) {
                result += m;
            }
            System.out.println(" " + result);
        }
    }


    private static boolean isPalindrome(String str, int m) {
        int left = 0;
        int right = m - 1;

        while (left <= right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private static boolean reverse(String s1, String s2, int m) {

        for (int i = 0; i < m; i++) {
            if (s1.charAt(i) != s2.charAt(m - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
