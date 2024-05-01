package programmers.javasolve.level2.hotelroom;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {

	static class Room implements Comparable<Room> {
		int startTime;
		int endTime;

		public Room(int startTime, int endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
		}

		@Override
		public int compareTo(Room o) {
			return Integer.compare(endTime, o.endTime);
		}
	}

	public int solution(String[][] book_time) {
		PriorityQueue<Room> queue = new PriorityQueue<>();
		Arrays.sort(book_time, Comparator.comparing(x -> x[0]));

		int answer = 0;

		for (String[] strings : book_time) {
			String start = strings[0];
			String end = strings[1];
			int startTime = convert(start);
			int endTime = convert(end) + 10;

			if (queue.isEmpty()) {
				answer++;
				queue.offer(new Room(startTime, endTime));
				continue;
			}

			Room prev = queue.poll();

			if (startTime >= prev.endTime) {
				queue.offer(new Room(startTime, endTime));
			} else {
				answer++;
				queue.offer(new Room(startTime, endTime));
				queue.offer(prev);
			}
		}

		return answer;
	}

	int convert(String time) {
		StringTokenizer st = new StringTokenizer(time, ":");
		int hour = Integer.parseInt(st.nextToken());
		int minute = Integer.parseInt(st.nextToken());
		return hour * 60 + minute;
	}

}

public class Main {
	public static void main(String[] args) {
		String[][] book_time = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"},
			{"18:20", "21:20"}};
		int answer = new Solution().solution(book_time);

		System.out.println("answer = " + answer);
	}
}
