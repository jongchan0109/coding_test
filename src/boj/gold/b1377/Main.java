package boj.gold.b1377;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int n;
    static Number[] arr;

    static class Number implements Comparable<Number>{
        int value;
        int index;

        Number(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Number o) {
            return this.value - o.value;
        }
    }


    public static void main(String[] args) throws IOException {
        input();

        Arrays.sort(arr);
        int max = 0;

        for (int i = 0; i < n; i++) {
            max = Math.max(arr[i].index - i, max);
        }
        System.out.println(max + 1);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new Number[n];

        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(br.readLine());
            arr[i] = new Number(value, i);
        }
    }
}

