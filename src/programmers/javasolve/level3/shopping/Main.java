package programmers.javasolve.level3.shopping;

import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        int dis = Integer.MAX_VALUE;
        Collections.addAll(set, gems);

        int kind = set.size();

        int left = 0;
        int right = 0;
        int[] answer = new int[]{0,0};

        while(true) {
            if (map.size() == kind) {
                if (map.get(gems[left]) == 1) {
                    map.remove(gems[left]);
                } else {
                    map.put(gems[left], map.get(gems[left]) - 1);
                }
                left++;
            } else if(right == gems.length) {
                break;

            } else {
                if (map.get(gems[right]) != null) {
                    map.put(gems[right], map.get(gems[right]) + 1);
                } else {
                    map.put(gems[right], 1);
                }
                right++;
            }

            if (map.size() == kind) {
                if (right - left < dis) {
                    dis = right - left;
                    answer[0] = left + 1;
                    answer[1] = right;
                }
            }
        }
        return answer;
    }
}


public class Main {
    public static void main(String[] args) {
        String[] gems = {"XYZ", "XYZ", "XYZ"};
        int[] result = new Solution().solution(gems);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
