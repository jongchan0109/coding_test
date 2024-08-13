package programmers.javasolve.level4.hotelroom;

import java.util.*;

class Solution {

    Map<Long, Long> map;


    public long[] solution(long[] roomNumber) {
        long[] answer = new long[roomNumber.length];

        map = new HashMap<>();

        for (int i = 0; i < roomNumber.length; i++) {
            long want = roomNumber[i];
            answer[i] = findRoom(want);
        }

        return answer;
    }

    long findRoom(long want) {

        if (!map.containsKey(want)) {
            map.put(want, want + 1);
            return want;
        }

        long empty = findRoom( map.get(want));
        map.put(want, empty);
        return empty;
    }

}

public class Main {
    public static void main(String[] args) {
        long[] roomNumber = {1,3,4,1,3,1};
        long[] result = new Solution().solution(roomNumber);
        System.out.println(Arrays.toString(result));
    }
}
