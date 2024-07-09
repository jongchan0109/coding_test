package boj.gold.b1033;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int n;
    static List<List<Integer>> graph;
    static long[] nums;


    public static void main(String[] args) {
        input();
        setNums();
        print();
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();
        graph = new ArrayList<>();
        nums = new long[n];

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            nums[i] = 1L;
        }

        for (int i = 0; i < n - 1; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            long p = scanner.nextInt();
            long q = scanner.nextInt();

            long gcd = gcd(p, q);

            calculation(from, to, p/gcd, q/gcd);

        }
        scanner.close();
    }

    private static long gcd(long n, long m) {
        if (n % m == 0) {
            return m;
        }
        return gcd(m, n % m);

    }

    private static void calculation(int from, int to, long p, long q){
        boolean[] check = new boolean[n];
        long tempA = nums[from];
        long tempB = nums[to];
        update(from, tempB*p, check);
        update(to, tempA*q, check);
        graph.get(from).add(to);
        graph.get(to).add(from);
    }

    private static void update(int target, long num, boolean[] visited) {
        nums[target] *= num;
        visited[target] = true;

        for (int next: graph.get(target)) {
            if (visited[next]) {
                continue;
            }
            update(next, num, visited);
        }

    }

    private static void print() {
        for (int i = 0; i < n; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    private static void setNums(){
        long gcd = gcd(nums[0], nums[1]);
        while(gcd > 1){

            for(int i = 0; i < n; i++) {
                gcd = gcd(gcd, nums[i]);
            }

            for(int i = 0; i < n; i++) {
                nums[i] /= gcd;
            }
        }
    }
}
