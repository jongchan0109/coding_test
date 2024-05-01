package programmers.javasolve.level1.numbersequence;

class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        int len = p.length();
        long pNum = Long.parseLong(p);



        for (int i = 0; i <= t.length() - len; i++) {
            String sub = t.substring(i,i+len);
            if (Long.parseLong(sub) <= pNum) {
                answer++;
            }

        }
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        String t = "3141592";
        String p = "271";
        int result = new Solution().solution(t,p);
        System.out.println("result = " + result);
    }
}
