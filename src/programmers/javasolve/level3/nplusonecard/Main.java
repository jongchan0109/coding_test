package programmers.javasolve.level3.nplusonecard;

import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int n = cards.length;
        int index = 0;

        List<Integer> currentCards = new ArrayList<>();
        List<Integer> availableCards = new ArrayList<>();

        for (; index < n / 3 ;index++) {
            currentCards.add(cards[index]);
        }

        do {
            answer++;
            boolean flag = false;

            if (index >= n) {
                break;
            }

            availableCards.add(cards[index++]);
            availableCards.add(cards[index++]);

            // case1: 처음 뽑은 카드 조사
            for (int i =0; i <currentCards.size(); i++) {
                int current = currentCards.get(i);
                if (currentCards.contains(n + 1 - current)) {
                    flag = true;
                    // 카드 2개 제거
                    currentCards.remove(i);
                    currentCards.remove(Integer.valueOf(n + 1 - current));
                    break;
                }
            }

            // case2: 코인 1개 사용
            if (!flag && coin >=1) {
                for (int i =0; i < currentCards.size() ; i++) {
                    int current = currentCards.get(i);
                    if (availableCards.contains(n + 1 - current)) {
                        currentCards.remove(i);
                        availableCards.remove(Integer.valueOf(n + 1 - current));
                        coin--;
                        flag = true;

                        break;
                    }
                }
            }

            // case 3 코인 2개 사용
            if (!flag && coin >= 2) {
                for (int i =0; i<availableCards.size(); i++) {
                    int current = availableCards.get(i);
                    if (availableCards.contains(n + 1 - current)) {
                        availableCards.remove(i);
                        availableCards.remove(Integer.valueOf(n + 1 - current));
                        coin -=2;
                        flag = true;
                        break;
                    }
                }
            }

            if (!flag) {
                break;
            }

        } while (true);

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        int coin = 4;
        int[] cards = {3,6,7,2,1,10,5,9,8,12,11,4};

        int result = new Solution().solution(coin,cards);

        System.out.println("result = " + result);
    }
}
