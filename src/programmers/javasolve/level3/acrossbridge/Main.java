package programmers.javasolve.level3.acrossbridge;

class Solution {
    public int solution(int[] stones, int k) {

        int left = 0;
        int right = 200000000;

        while (left < right) {
            int mid = (left + right) / 2;

            if (across(stones, mid, k)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        return left;
    }

    public boolean across(int[] stones, int people, int k) {

        int sequence = 0;
        int maxSequence = 0;

        for (int stone : stones) {
            if (stone - people <= 0) {
                sequence++;
                maxSequence = Math.max(sequence, maxSequence);
            } else {
                sequence = 0;
            }
        }
        return maxSequence < k;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] stones = {2,4,5,3,2,1,4,2,5,1};
        int k = 3;
        int result = new Solution().solution(stones, k);
        System.out.println("result = " + result);
    }
}
