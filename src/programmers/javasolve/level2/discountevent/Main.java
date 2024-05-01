package programmers.javasolve.level2.discountevent;

import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        HashMap<String, Integer> mart = new HashMap<>();
        HashMap<String, Integer> purchase = new HashMap<>();

        for (int i = 0; i < want.length; i++) {
            purchase.put(want[i], number[i]);
        }

        for (int i = 0; i < discount.length; i++) {

            if (mart.get(discount[i]) == null) {
                mart.put(discount[i], 1);
            } else {
                mart.put(discount[i], mart.get(discount[i]) + 1);
            }

            if (i < 9) {
                continue;
            }
            boolean flag = true;
            for (String wantItem : want) {
                if (!purchase.get(wantItem).equals(mart.get(wantItem))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                answer++;
            }

            String remove = discount[i - 9];
            if (mart.get(remove) == 1) {
                mart.remove(remove);
            } else {
                mart.put(remove, mart.get(remove) - 1);
            }

        }
        return answer;
    }
}

public class Main {

    public static void main(String[] args) {
        String[] want = {"banana", "apple", "rice", "pork", "pot"};
        int[] number = {3,2,2,2,1};
        String[] discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};
        int result = new Solution().solution(want,number,discount);

        System.out.println("result = " + result);
    }
}
