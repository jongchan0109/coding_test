package javasolve.level2.pccpnum3;

class Solution {

    int calculateCount(int time){

        int minuteCount = time * 59 / 3600;
        int hourCount = time * 719 / (3600 * 12);
        int minuteAndHour = time / (3600*12);

        return minuteCount + hourCount - minuteAndHour;
    }
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int timeOne = h1 * 3600 + m1 * 60 + s1;
        int tomeTwo = h2 * 3600 + m2 * 60 + s2;
        int now = 0;

        if(timeOne * 719 % (3600*12) == 0 || timeOne * 59 % 3600 ==0)
            now = 1;

        return calculateCount(tomeTwo) - calculateCount(timeOne) + now;
    }
}

public class Main {
    public static void main(String[] args) {
        int h1 = 0;
        int m1 = 0;
        int s1 = 0;
        int h2 = 23;
        int m2 = 59;
        int s2 = 59;
        int result = new Solution().solution(h1, m1, s1, h2, m2, s2);

        System.out.println(result);
    }
}
