package programmers.javasolve.level3.traffic;

import java.util.*;

class Solution {
    public int solution(String[] lines) {
        List<Integer> start = new ArrayList<>();
        List<Integer> end = new ArrayList<>();
        int answer = 0;

        for (String line : lines) {
            int hour = Integer.parseInt(line.substring(11, 13));
            int minute = Integer.parseInt(line.substring(14, 16));
            int sec = Integer.parseInt(line.substring(17, 19));
            int milli = Integer.parseInt(line.substring(20, 23));

            double dur = Double.parseDouble(line.substring(24, line.length() - 1));
            int time = ((60 * hour + minute) * 60 + sec) * 1000 + milli;
            end.add(time);
            start.add(time - (int) (dur * 1000) + 1);
        }

        answer = getAnswer(start, end, answer, true);
        answer = Math.max(answer, getAnswer(start, end, answer, false));

        return answer;
    }

    private int getAnswer(List<Integer> start, List<Integer> end, int answer, boolean flag) {
        for (int i = 0; i < start.size(); i++) {
            int startRange;
            if (flag) {
                startRange = start.get(i);
            } else {
                startRange = end.get(i);
            }
            int endRange = startRange + 999;

            int count = 0;
            for (int j = 0; j < start.size(); j++) {
                int sRange = start.get(j);
                int eRange = end.get(j);

                if (sRange <= endRange && eRange >= startRange) {
                    count++;
                }
            }
            answer = Math.max(answer, count);

        }
        return answer;
    }
}


public class Main {
    public static void main(String[] args) {
        String[] lines = {"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"};
        int result = new Solution().solution(lines);
        System.out.println("result = " + result);
    }
}
