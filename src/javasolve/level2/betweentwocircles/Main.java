package javasolve.level2.betweentwocircles;

class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;

        for (int i = 1; i <= r2; i++) {
            int bigX = (int)Math.sqrt(Math.pow(r2,2) -  Math.pow(i,2));
            double smallX = Math.sqrt(Math.pow(r1,2) - Math.pow(i,2));
            answer +=  bigX - (int)Math.ceil(smallX) + 1;
        }

        return answer * 4L;
    }
}

public class Main {
    public static void main(String[] args) {
        int r1 = 2;
        int r2 = 3;
        long result = new Solution().solution(r1, r2);
        System.out.println("result = " + result);
    }
}
