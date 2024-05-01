package programmers.javasolve.level3.camping;

import java.util.*;

class Solution {
    public int solution(int[][] data) {
        int answer = 0;

        Arrays.sort(data,(o1,o2)-> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);

        for (int i = 0; i < data.length - 1; i++) {
            for (int j = i +1; j < data.length; j++) {
                int[] start = data[i];
                int[] end = data[j];

                if (area(start,end) == 0) {
                    continue;
                }
                if (include(start, end, data, i, j)) {
                    continue;
                }
                answer++;
            }
        }

        return answer;
    }

    private long area(int[] start, int[] end) {
        return (long)(Math.abs(start[0] - end[0])) * (long)(Math.abs(start[1]-end[1]));
    }

    private boolean include(int[] start, int[] end, int[][] data, int from, int to) {

        if (end[1] > start[1]) {
            for (int i = from + 1; i < to + 1; i++) {
                if (start[0] < data[i][0] && data[i][0] < end[0] && start[1] < data[i][1] && data[i][1] < end[1]) {
                    return true;
                }

            }
        } else {
            for (int i = from + 1; i < to; i++) {
                if (start[0] < data[i][0] && data[i][0] < end[0] && end[1] < data[i][1] && data[i][1] < start[1]) {
                    return true;
                }
            }

        }

        return false;
    }

}


public class Main {
    public static void main(String[] args) {
        int[][] data = {{0,0}, {1,1}, {0,2}, {2,0}};
        int result = new Solution().solution(data);
        System.out.println("result = " + result);
    }
}
