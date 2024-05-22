package boj.gold.b16434;

import java.util.Scanner;

public class Main {

    static int[][] rooms;
    static int n;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        int atk = scanner.nextInt();
        rooms = new int[n][3];

        for (int i = 0; i < n; i++) {
            rooms[i][0] = scanner.nextInt();
            rooms[i][1] = scanner.nextInt();
            rooms[i][2] = scanner.nextInt();
        }

        long left = 1;
        long right = (long) 1e18;
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (canSurvive(mid, atk)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    public static boolean canSurvive(long maxHp, long atk) {
        long currentHp = maxHp;

        for (int i = 0; i < n; i++) {
            int type = rooms[i][0];
            int a = rooms[i][1];
            int h = rooms[i][2];

            if (type == 1) {
                // 몬스터와 전투
                long turnsToKillMonster = (h + atk - 1) / atk; // 몬스터를 죽이는 데 필요한 턴 수
                long turnsToBeKilled = (currentHp + a - 1) / a; // 용사가 죽는 데 필요한 턴 수

                if (turnsToBeKilled < turnsToKillMonster) {
                    return false;
                }

                currentHp -= (turnsToKillMonster - 1) * a;
            } else if (type == 2) {
                // 포션
                atk += a;
                currentHp = Math.min(currentHp + h, maxHp);
            }
        }

        return true;
    }
}

