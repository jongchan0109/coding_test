package javasolve.level2.homework;

import java.util.*;

class Solution {

    static class Homework implements Comparable<Homework> {
        public final String subject;
        public int startTime;
        public int playTime;

        public Homework(String subject, String time, int playTime) {
            this.subject = subject;
            StringTokenizer st = new StringTokenizer(time, ":");
            this.startTime = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
            this.playTime = playTime;
        }

        @Override
        public int compareTo(Homework o) {
            return Integer.compare(this.startTime, o.startTime);
        }
    }

    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        int index = 0;

        List<Homework> homeworkList = new LinkedList<>();
        Stack<Homework> stopHomework = new Stack<>();

        for (String[] plan : plans) {
            homeworkList.add(new Homework(plan[0], plan[1], Integer.parseInt(plan[2])));
        }
        Collections.sort(homeworkList);

        for (int i = 0; i < plans.length; i++) {

            Homework current = homeworkList.remove(0);

            int nowTime = current.startTime;
            int nextTime;
            if (homeworkList.isEmpty()) {
                nextTime = Integer.MAX_VALUE;
            } else {
                nextTime = homeworkList.get(0).startTime;
            }
            int remainTime = nextTime - nowTime;

            stopHomework.push(current);


            while (!stopHomework.isEmpty() && remainTime > 0) {

                Homework nowWorking = stopHomework.pop();
                int played = Math.min(remainTime, nowWorking.playTime);

                if (played == nowWorking.playTime) {
                    answer[index++] = nowWorking.subject;
                } else {
                    nowWorking.playTime -= played;
                    stopHomework.add(nowWorking);
                }
                remainTime -= played;
            }

        }

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        String[][] plans = {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}};
        String[] result = new Solution().solution(plans);

        for (String s : result) {
            System.out.print(s + " ");
        }
    }
}
