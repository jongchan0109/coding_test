package programmers.javasolve.level3.ballon;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int min = 1000000000;
        int minIdx = -1;

        for (int i = 0; i < a.length; i++) {
            if (a[i] <= min) {
                min = a[i];
                minIdx = i;
            }
        }

        int minLeft = 1000000000;
        int minRight = minLeft;

        for (int i = 0; i < minIdx; i++) {
            if (a[i] < minLeft) {
                answer++;
                minLeft = a[i];
            }
        }

        for (int i = a.length - 1; i > minIdx; i--) {
            if (a[i] < minRight) {
                answer++;
                minRight = a[i];
            }
        }

        return answer + 1;
    }
}


public class Main {
    public static void main(String[] args) {
        int[] a = {9, -1, 5};
        int result = new Solution().solution(a);
        System.out.println("result = " + result);
    }
}
