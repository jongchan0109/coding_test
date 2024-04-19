package javasolve.level3.bestset;

class Solution {
    public int[] solution(int n, int s) {

        if (n > s) {
            return new int[] {-1};
        }

        int[] answer = new int[n];
        int index = 0;
        while (n > 0) {
            answer[index] = s / n;
            n--;
            s-= answer[index];
            index++;
        }
        return answer;
    }
}




public class Main {
    public static void main(String[] args) {
        int n = 2;
        int s = 9;
        int[] result = new Solution().solution(n,s);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
