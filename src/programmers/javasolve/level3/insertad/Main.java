package programmers.javasolve.level3.insertad;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int totalTime = timeToSecond(play_time);
        int adTime = timeToSecond(adv_time);
        long[] watchedTime = new long[totalTime + 1];

        for (String log : logs) {
            String[] times = log.split("-");
            int start = timeToSecond(times[0]);
            int end = timeToSecond(times[1]);
            watchedTime[start]++;
            watchedTime[end]--;
        }

        for (int i = 1; i <= totalTime; i++) {
            watchedTime[i] += watchedTime[i - 1];
        }

        long maxTime = 0;
        long sum = 0;

        for (int i = 0; i < adTime; i++) {
            sum += watchedTime[i];
        }

        long maxSum = sum;

        for (int i = adTime; i <= totalTime; i++) {
            sum += watchedTime[i] - watchedTime[i - adTime];
            if (sum > maxSum) {
                maxSum = sum;
                maxTime = i - adTime + 1;
            }
        }

        return secondToTime((int) maxTime);
    }

    public String secondToTime(int second) {
        int hour = second / 3600;
        second %= 3600;
        int minute = second / 60;
        second %= 60;

        return timeToString(hour) + ":" + timeToString(minute) + ":" + timeToString(second);
    }

    public String timeToString(int time) {
        return time < 10 ? "0" + time : String.valueOf(time);
    }

    public int timeToSecond(String time) {
        int sum = 0;
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(3, 5));
        int second = Integer.parseInt(time.substring(6, 8));
        sum += 3600 * hour + 60 * minute + second;
        return sum;
    }
}

public class Main {
    public static void main(String[] args) {
        String playTime = "02:03:55";
        String adTime = "00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};

        String result = new Solution().solution(playTime, adTime, logs);
        System.out.println("result = " + result);
    }
}
