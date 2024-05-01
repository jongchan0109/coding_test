package programmers.javasolve.level3.consultant;

import java.util.*;

class Solution {

    static class Time {
        int start;
        int end;

        Time(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public int solution(int k, int n, int[][] reqs) {
        int answer = 0;

        HashMap<Integer, List<Time>> map = new HashMap<>();

        for (int i = 1; i <= k; i++) {
            List<Time> list = new ArrayList<>();
            map.put(i, list);
        }

        for (int[] req : reqs) {
            int type = req[2];
            map.get(type).add(new Time(req[0], req[1]));
        }

        int[][] mentor = new int[k + 1][2];// 행에는 인덱스번째의 멘토 수, 열에는 현재 걸리는 시간

        for (int i = 1; i <= k; i++) {
            mentor[i][0] = 1;
            mentor[i][1] = calculateTime(map.get(i), 1);
        }

        for (int i = k + 1; i <= n; i++) {

            int min = 0;
            int minIdx = 1;

            for (int j = 1; j <= k; j++) {
                int value = calculateTime(map.get(j), mentor[j][0]+1);
                if (mentor[j][1] - value > min) {
                    min = mentor[j][1] - value;
                    minIdx = j;
                }
            }
            mentor[minIdx][0] += 1;
            mentor[minIdx][1] = calculateTime(map.get(minIdx), mentor[minIdx][0]);
        }


        for (int i = 1; i <= k; i++) {
            answer += calculateTime(map.get(i), mentor[i][0]);
        }


        return answer;
    }

    int calculateTime(List<Time> list, int mentor) {

        if (list.isEmpty()) {
            return 0;
        }
        int sum = 0;

        HashMap<Integer, List<Time>> map = new HashMap<>();

        for (int i = 0; i < mentor; i++) {
            map.put(i, new ArrayList<>());
        }

        for (Time time : list) {
            boolean flag = false;
            for (int i = 0; i < mentor; i++) {
                if (map.get(i).isEmpty()) {
                    map.get(i).add(time);
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            }

            int min = Integer.MAX_VALUE;
            int minIdx = 0;

            for (int i = 0; i < mentor; i++) {
                if (endTime(map.get(i)) < min) {
                    min = endTime(map.get(i));
                    minIdx = i;
                }
            }
            map.get(minIdx).add(time);
        }

        for (int i = 0; i < mentor; i++) {
            sum += calculateTime(map.get(i));
        }

        return sum;
    }

    int calculateTime(List<Time> list) {

        if (list.isEmpty()) {
            return 0;
        }

        int sum = 0;
        int current = 0;

        for (Time time : list) {
            if (time.start <= current) {
                sum += (current - time.start);
                current += time.end;
            } else {
                current = time.start + time.end;
            }
        }

        return sum;
    }

    int endTime(List<Time> list) {
        int current = 0;
        for (Time time : list) {
            if (time.start <= current) {
                current += time.end;
            } else {
                current = time.start + time.end;
            }
        }
        return current;
    }
}

public class Main {
    public static void main(String[] args) {
        int k = 3;
        int n = 5;
        int[][] reqs = {{10, 60, 1}, {15, 100, 3}, {20, 30, 1}, {30, 50, 3}, {50, 40, 1}, {60, 30, 2}, {65, 30, 1}, {70, 100, 2}};

        int result = new Solution().solution(k, n, reqs);

        System.out.println("result = " + result);
    }
}
