package javasolve.level2.selfgame;

import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int n = cards.length;
        int[] box = new int[n + 1];
        int answer = 0;

        System.arraycopy(cards, 0, box, 1, n);

        for (int i = 1; i <= n; i++) {
            boolean[] visit = new boolean[n + 1];
            int current = i;
            int count1 = 0;
            visit[current] = true;
            count1++;

            do {
                current = box[current];
                if (visit[current]) {
                    break;
                }
                visit[current] = true;
                count1++;
            } while (current != i);

            if (count1 == n) {
                continue;
            }

            int count2;

            for (int j = 1; j <= n; j++) {
                if(visit[j]){
                    continue;
                }
                List<Integer> reset = new ArrayList<>();

                count2 = 0;
                current = j;
                visit[current] = true;
                reset.add(current);
                count2++;
                do {
                    current = box[current];
                    if (visit[current]) {
                        break;
                    }
                    visit[current] = true;
                    reset.add(current);
                    count2++;
                } while (current != j);

                for (Integer resetElement : reset) {
                    visit[resetElement] = false;
                }
                answer = Math.max(count1 * count2, answer);
            }
        }
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] cards = {8, 6, 3, 7, 2, 5, 1, 4};
        int result = new Solution().solution(cards);

        System.out.println("result = " + result);
    }
}

