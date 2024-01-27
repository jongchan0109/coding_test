package javasolve.level2.subsequence;

class Solution {
    public int solution(int[] elements) {
        int n = elements.length;
        int sum = 0;
        int answer = 0;
        for (int element : elements) {
            sum += element;
        }
        boolean[] subsequence = new boolean[sum + 1];

        for (int i = 0; i < n; i++) { // 시작 원소
            int sumOfElement = 0;
            for (int j = 0; j < n; j++) { //길이
                sumOfElement += elements[(i + j) % n];
                subsequence[sumOfElement] = true;
            }
        }
        for (int i = 1; i <= sum; i++) {
            if (subsequence[i]) {
                answer++;
            }
        }
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] elements = {7, 9, 1, 1, 4};
        int result = new Solution().solution(elements);

        System.out.println("result = " + result);
    }
}
