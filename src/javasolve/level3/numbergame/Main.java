package javasolve.level3.numbergame;

import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        int aIdx = 0;
        int bIdx = 0;

        while (bIdx < B.length) {
            if (A[aIdx] < B[bIdx]) {
                aIdx++;
                answer++;
            }
            bIdx++;
        }
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] A = {5, 1, 3, 7};
        int[] B = {2, 2, 6, 8};
        int result = new Solution().solution(A, B);
        System.out.println("result = " + result);
    }
}
