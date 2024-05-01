package programmers.javasolve.level2.nsquarearray;

class Solution {
    public int[] solution(int n, long left, long right) {
        int len = (int)(right - left) + 1;
        int[] answer = new int[len];
        int index = 0;

        for (long i = left ; i <= right; i++, index++) {
            int row = (int) (i / n);
            int col = (int) (i % n);
            answer[index] = Math.max(row,col) + 1;
        }
        return answer;

    }
}

public class Main {
    public static void main(String[] args) {
        int n = 3;
        long left = 2L;
        long right = 5L;
        int[] result = new Solution().solution(n,left,right);

        for (int i : result) {
            System.out.print(i+" ");
        }
    }
}
