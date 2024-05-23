package boj.gold.b8980;

import java.util.*;

public class Main {


    static class Delivery implements Comparable<Delivery> {
        int from;
        int to;
        int box;

        public Delivery(int from, int to, int box) {
            this.from = from;
            this.to = to;
            this.box = box;
        }


        @Override
        public int compareTo(Delivery o) {
            return this.to == o.to ? this.from - o.from : this.to - o.to;
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int c = scanner.nextInt();
        int m = scanner.nextInt();
        int sum = 0;
        int[] capacities = new int[n];

        Delivery[] delivery = new Delivery[m];

        for (int i = 0; i < m; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int box = scanner.nextInt();
            delivery[i] = new Delivery(from, to, box);
        }
        Arrays.sort(delivery);

        Arrays.fill(capacities, c);

        for (int i = 0; i < m; i++) {
            int from = delivery[i].from;
            int to = delivery[i].to;
            int box = delivery[i].box;

            int capacity = Integer.MAX_VALUE;

            for (int j = from; j < to; j++) {
                capacity = Math.min(capacity, capacities[j]);
            }

            for (int j = from; j < to; j++) {
                capacities[j] -= Math.min(capacity, box);
            }

            sum += Math.min(capacity, box);

        }

        System.out.println(sum);


    }

}