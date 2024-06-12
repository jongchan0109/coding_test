package boj.gold.b14658;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static class Star {
        int x;
        int y;

        public Star(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static int m;
    static int l;

    static List<Star> stars;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();
        m = scanner.nextInt();
        l = scanner.nextInt();
        int k = scanner.nextInt();
        stars = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            stars.add(new Star(x,y));
        }

        int result = k - solve();

        System.out.println(result);

    }


    public static int solve() {

        int max = 0;

        for (Star s1 : stars) {
            for (Star s2 : stars) {
                int count = 0;

                int x = s1.x;
                int y = s2.y;

                int endX = x + l;
                int endY = y + l;


                for (Star s : stars) {
                    if (range(x, y, endX, endY, s)) {
                        count++;
                    }
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }

    public static boolean range(int startX, int startY, int endX, int endY, Star star) {
        return startX <= star.x && startY <= star.y && endX >= star.x && endY >= star.y;
    }

}


