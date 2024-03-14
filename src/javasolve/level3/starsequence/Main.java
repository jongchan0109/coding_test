package javasolve.level3.starsequence;

class Solution {

    public int solution(int[] a) {
        int n = a.length;
        int[] count = new int[n];
        int answer = 0;

        for (int num : a) {
            count[num]++;
        }

        for (int i = 0; i < n; i++) {
            if (count[i] == 0 || count[i] <= answer) {
                continue;
            }
            int length = 0;

            for (int j = 0; j < n - 1; j++) {

                if (a[j] != i && a[j + 1] != i || a[j] == a[j + 1]) {
                    continue;
                }
                length++;
                j++;
            }
            answer = Math.max(answer, length);

        }

        return answer * 2;
    }
}


public class Main {
    public static void main(String[] args) {
        int[] a = {0, 3, 3, 0, 7, 2, 0, 2, 2, 0};
        int result = new Solution().solution(a);
        System.out.println("result = " + result);
    }
}
