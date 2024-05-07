package swea.d4.d4_18799;

import java.util.*;

class Solution {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int sum = 0;

            for (int i = 0; i < n; i++) {
                int num = sc.nextInt();
                sum += num;
            }

            if (sum == 0) {
                System.out.println("#" + test_case + " " + 0);
                continue;
            }

            double average = (double) sum / n;

            String averageStr = String.format("%.20f", average);

            averageStr = removeTrailingZeros(averageStr);

            System.out.println("#" + test_case + " " + averageStr);


        }
    }

    private static String removeTrailingZeros(String number) {
        if (!number.contains(".")) {
            return number;
        }

        int index = number.length() - 1;
        while (index >= 0 && (number.charAt(index) == '0' || number.charAt(index) == '.')) {
            index--;
            if (number.charAt(index + 1) == '.') {
                break;
            }
        }

        return number.substring(0, index + 1);
    }

}
