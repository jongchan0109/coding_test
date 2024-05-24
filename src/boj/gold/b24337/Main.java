package boj.gold.b24337;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();


        List<Integer> list = new ArrayList<>();

        for (int i = 1; i < a; i++) {
            list.add(i);
        }
        list.add(Math.max(a,b));
        for (int i = b - 1; i > 0; i--) {
            list.add(i);
        }

        if (list.size() > n) {
            System.out.println(-1);
            return;
        }

        if (a == 1) {
            while (list.size() < n) {
                list.add(1, 1);
            }
        } else {
            while (list.size() < n) {
                list.add(0, 1);
            }
        }

        for (Integer i : list) {
            System.out.print(i + " ");
        }


    }

}
