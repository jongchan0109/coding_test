package javasolve.level3.camera;

import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparingInt(a -> a[1]));
        int answer = 0;
        int cam = -30001;
        for (int[] route : routes) {
            if (route[0] <= cam) {
                continue;
            }
            cam = route[1];
            answer++;
        }

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] routes = {{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}};
        int result = new Solution().solution(routes);
        System.out.println("result = " + result);
    }
}
