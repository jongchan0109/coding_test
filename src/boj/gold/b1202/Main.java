package boj.gold.b1202;

import java.util.*;

public class Main {

    static int n;
    static int k;
    static long result;

    static List<Jewel> jewels;
    static List<Integer> backpack;

    static class Jewel implements Comparable<Jewel> {
        int weight;
        int value;

        Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewel o) {
            return this.weight - o.weight;  // 무게 기준 오름차순 정렬
        }
    }

    public static void main(String[] args) {
        input();
        solve();
        System.out.println(result);
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();
        result = 0;

        jewels = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int weight = scanner.nextInt();
            int value = scanner.nextInt();
            jewels.add(new Jewel(weight, value));
        }

        backpack = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            backpack.add(scanner.nextInt());
        }

        Collections.sort(jewels);
        Collections.sort(backpack);
    }

    private static void solve() {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int jewelIndex = 0;

        for (int bagCapacity : backpack) {
            while (jewelIndex < jewels.size() && jewels.get(jewelIndex).weight <= bagCapacity) {
                maxHeap.offer(jewels.get(jewelIndex).value);
                jewelIndex++;
            }
            if (!maxHeap.isEmpty()) {
                result += maxHeap.poll();
            }
        }
    }
}
