package javasolve.level3.longestpalindrome;

class Solution
{
    public int solution(String s)
    {
        int answer = 1;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (isPalindrome(s,i,j)) {
                    answer = Math.max(answer, j - i + 1);
                }
            }
        }

        return answer;

    }

    private boolean isPalindrome(String s, int start, int end) {

        while (start < end) {
            if (s.charAt(end) != s.charAt(start)) {
                return false;
            }
            start++;
            end--;
        }
        return true;

    }

}

public class Main {
    public static void main(String[] args) {
        String s = "level";
        int answer = new Solution().solution(s);
        System.out.println("answer = " + answer);
    }
}
