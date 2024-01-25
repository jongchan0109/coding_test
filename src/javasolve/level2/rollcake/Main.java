package javasolve.level2.rollcake;

import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int top : topping) {
            map.merge(top, 1, Integer::sum);
        }

        HashMap<Integer, Integer> divideMap = new HashMap<>();

        for (int top : topping) {
            divideMap.putIfAbsent(top, 1);
            map.put(top, map.get(top) - 1);
            if (map.get(top) == 0) {
                map.remove(top);
            }
            if (map.size() == divideMap.size()) {
                answer++;
            }
        }
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] topping = {1, 2, 1, 3, 1, 4, 1, 2};
        int result = new Solution().solution(topping);
        System.out.println("result = " + result);
    }
}
