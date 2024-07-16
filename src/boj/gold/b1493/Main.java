package boj.gold.b1493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int length;
    static int width;
    static int height;

    static int n;

    static int[] cube;
    static int result;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        input();
        flag = true;
        solve(length, width, height);

        if (!flag) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");

        length = Integer.parseInt(strings[0]);
        width = Integer.parseInt(strings[1]);
        height = Integer.parseInt(strings[2]);
        result = 0;
        flag = false;

        n = Integer.parseInt(br.readLine());
        cube = new int[21];

        for (int i = 0; i < n; i++) {
            strings = br.readLine().split(" ");
            int size = Integer.parseInt(strings[0]);
            int count = Integer.parseInt(strings[1]);

            cube[size] = count;
        }

    }

    private static void solve(int l, int w, int h) {

        if (l == 0 || w == 0 || h == 0) {
            return;
        }

        if (!flag) {
            return;
        }

        flag = false;

        int cubeLength = 0;

        int minLength = Math.min(l, Math.min(w,h));

        for (int i = 20; i >= 0; i--) {
            if (cube[i] == 0)
                continue;

            cubeLength = 1 << i;
            if (minLength >= cubeLength) {
                result++;
                cube[i]--;
                flag = true;
                break;
            }
        }

        if (!flag) {
            return;
        }

        solve(l, w, h - cubeLength);

        solve(l - cubeLength, w, cubeLength);

        solve(cubeLength, w - cubeLength, cubeLength);
    }
}

