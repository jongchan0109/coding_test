package boj.gold.b1700;

import java.util.*;

public class Main {

    static int n;  // 멀티탭 구멍의 개수
    static int k;  // 전기용품 사용 횟수
    static int[] arr;  // 전기용품 사용 순서 배열

    public static void main(String[] args) {
        input();
        int result = solve();
        System.out.println(result);
    }

    public static void input() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();
        arr = new int[k];

        for (int i = 0; i < k; i++) {
            arr[i] = scanner.nextInt();
        }
    }

    public static int solve() {
        int count = 0;
        List<Integer> tab = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (tab.contains(arr[i])) {
                continue;
            }

            if (tab.size() < n) {
                tab.add(arr[i]);
                continue;
            }

            int itemToRemove = getItemToRemove(tab, i);

            tab.remove((Integer) itemToRemove);
            tab.add(arr[i]);
            count++;
        }

        return count;
    }

    private static int getItemToRemove(List<Integer> tab, int i) {
        int maxIdx = -1;
        int itemToRemove = -1;

        for (Integer integer : tab) {
            int nextUseIdx = Integer.MAX_VALUE;

            for (int l = i + 1; l < k; l++) {
                if (arr[l] == integer) {
                    nextUseIdx = l;
                    break;
                }
            }

            if (nextUseIdx > maxIdx) {
                maxIdx = nextUseIdx;
                itemToRemove = integer;
            }
        }
        return itemToRemove;
    }
}
