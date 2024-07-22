package boj.gold.b1275;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] arr;
    static long[] tree;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];
        tree = new long[4 * n];

        st = new StringTokenizer(bf.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        makeTree(1, 1, n);


        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(bf.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            if (start > end) {
                int temp = start;
                start = end;
                end = temp;
            }
            long sum = query(1, 1, n, start, end);
            sb.append(sum).append("\n");


            update(1, 1, n, number, value);
        }

        System.out.println(sb);
    }

    private static void makeTree(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        makeTree(2 * node, start, mid);
        makeTree(2 * node + 1, mid + 1, end);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    private static long query(int node, int l, int r, int start, int end) {

        if (end < l || r < start) {
            return 0L;
        }
        if (start <= l && r <= end) {
            return tree[node];
        }

        int mid = (l + r) / 2;
        long leftSum = query(2 * node, l, mid, start, end);
        long rightSum = query(2 * node + 1, mid + 1, r, start, end);
        return leftSum + rightSum;

    }

    private static void update(int node, int start, int end, int number, int value) {

        if (start == end) {
            arr[number] = value;
            tree[node] = value;
            return;
        }
        int mid = (start + end) / 2;

        if (start <= number && number <= mid) {
            update(2 * node, start, mid, number, value);
        } else {
            update(2 * node + 1, mid + 1, end, number, value);
        }
        tree[node] = tree[2 * node] + tree[2 * node + 1];

    }
}
