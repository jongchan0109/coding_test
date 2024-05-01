package programmers.javasolve.level2.primenumber;

import java.util.*;

class Solution {
    public int solution(int n, int k) {
        List<Integer> base = convert(n, k);
        long sum = 0;
        int answer = 0;

        for (int num : base) {
            if (num == 0) {
                if (isPrime(sum)) {
                    answer++;
                }
                sum = 0;
            } else {
                sum = 10 * sum + num;
            }
        }
        if (isPrime(sum)) {
            answer++;
        }
        return answer;
    }

    public List<Integer> convert(int n, int k) {
        List<Integer> convertToBase = new ArrayList<>();
        while (n != 0) {
            convertToBase.add(n % k);
            n /= k;
        }
        Collections.reverse(convertToBase);
        return convertToBase;
    }

    public boolean isPrime(long n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        int n = 437674;
        int k = 3;
        int result = new Solution().solution(n, k);

        System.out.println("result = " + result);
    }
}
