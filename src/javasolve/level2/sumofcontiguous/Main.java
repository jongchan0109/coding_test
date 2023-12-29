package javasolve.level2.sumofcontiguous;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int left = 0;
        int right = 1;
        int[] answer = {left, sequence.length-1};

        int sum = sequence[0];

        while (left < right) {
            if (sum == k) {
                change(right, left, answer);
                sum -= sequence[left++];
            } else if (sum > k) {
                sum -= sequence[left++];
            } else if (right < sequence.length) {
                sum += sequence[right++];
            } else {
                break;
            }
        }
        return answer;
    }

    private void change(int right, int left, int[] answer) {
        if (right - 1 - left < answer[1] - answer[0]) {
            answer[0] = left;
            answer[1] = right - 1;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int[] sequence = {1, 2, 3, 4, 5,};
        int k = 7;
        int[] result = new Solution().solution(sequence, k);

        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
