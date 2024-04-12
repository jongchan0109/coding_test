package javasolve.level3.stationinstall;

class Solution {

    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int start = 1;

        for (int station: stations) {
            if (start < station - w) {
                answer += install(start, station - w - 1, w);
            }
            start = station + w + 1;
        }

        if (stations[stations.length - 1] + w < n) {
            answer += install(stations[stations.length - 1] + w + 1, n, w);

        }

        return answer;
    }

    int install(int start, int end, int w) {
        int length = 2 * w + 1;
        int dis = end - start + 1;

        if (dis % length == 0) {
            return dis / length;
        } else {
            return dis / length + 1;
        }
    }

}


public class Main {
    public static void main(String[] args) {
        int n = 11;
        int[] stations = {4,11};
        int w = 1;
        int result = new Solution().solution(n,stations,w);
        System.out.println("result = " + result);
    }
}
