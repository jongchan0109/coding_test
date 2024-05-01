package programmers.javasolve.level3.shuttlebus;

import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }

        int[] time = new int[timetable.length];
        for (int i = 0; i < timetable.length; i++) {
            time[i] = stringToTime(timetable[i]);
        }
        Arrays.sort(time);

        int index = 0;

        for (int i = 0; i < n; i++) {
            while (true) {
                int departTime = 540 + i * t;
                if (index >= timetable.length) {
                    break;
                }
                if (time[index] <= departTime && map.get(i).size() < m) {
                    map.get(i).add(time[index]);
                    index++;
                } else {
                    break;
                }
            }
        }
        if (map.get(n - 1).size() < m) {
            return timeToString(540 + (n - 1) * t);
        } else {
            List<Integer> list = map.get(n - 1);
            Collections.sort(list);
            return timeToString(list.get(m - 1) - 1);
        }

    }

    private String timeToString(int time) {
        String result;
        int hour = time / 60;
        int minute = time % 60;
        if (hour < 10) {
            result = "0" + hour + ":";
        } else {
            result = hour + ":";
        }
        if (minute < 10) {
            result += "0" + minute;
        } else {
            result += minute;
        }
        return result;
    }

    private int stringToTime(String time) {
        String[] hourMinute = time.split(":");
        int hour = Integer.parseInt(hourMinute[0]) * 60;
        int minute = Integer.parseInt(hourMinute[1]);
        return hour + minute;
    }
}


public class Main {
    public static void main(String[] args) {
        int n = 1;
        int t = 1;
        int m = 5;
        String[] timeTable = {"08:00", "08:01", "08:02", "08:03"};
        String result = new Solution().solution(n, t, m, timeTable);
        System.out.println("result = " + result);
    }
}
