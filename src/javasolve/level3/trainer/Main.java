package javasolve.level3.trainer;

import java.util.*;

class Solution {

    public int solution(int n, int[][] timetable) {
        int people = maxPeople(timetable);

        if (people == 1) {
            return 0;
        }

        int left = 0;
        int right = 2 * (n - 1);

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canSet(mid, n, people)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    private boolean canSet(int mid, int n, int people) {

        for (int startX = 0; startX < n; startX++) {
            List<int[]> list = new ArrayList<>();
            list.add(new int[]{0, startX});

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    boolean flag = true;

                    for (int[] point : list) {
                        int dis = Math.abs(point[0] - i) + Math.abs(point[1] - j);
                        if (dis < mid) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        list.add(new int[]{i, j});
                        if (list.size() == people) {
                            return true;
                        }
                    }
                }
            }

        }
        return false;
    }

    private int maxPeople(int[][] timetable) {
        int people = 0;
        int[] time = new int[1321];

        for (int[] t : timetable) {
            for (int i = t[0]; i <= t[1]; i++) {
                time[i]++;
            }
        }
        for (int i = 600; i <= 1320; i++) {
            people = Math.max(people, time[i]);
        }
        return people;
    }
}


public class Main {
    public static void main(String[] args) {
        int n = 3;
        int[][] timetable = {{1170, 1210}, {1200, 1260}};
        int result = new Solution().solution(n, timetable);
        System.out.println("result = " + result);
    }
}
