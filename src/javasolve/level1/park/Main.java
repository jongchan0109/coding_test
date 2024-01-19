package javasolve.level1.park;

class Solution {
	public int[] solution(String[] park, String[] routes) {
		int[] answer = new int[] {0, 0};

		for (int i = 0; i < park.length; i++) {
			for (int j = 0; j < park[i].length(); j++) {
				if (park[i].charAt(j) == 'S') {
					answer[0] = i;
					answer[1] = j;
					break;
				}
			}
		}

		for (String route : routes) {
			char op = route.charAt(0);
			int index = route.charAt(2) - '0';

			int[] next = new int[] {answer[0], answer[1]};

			switch (op) {
				case 'E':
					next[1] += index;
					break;
				case 'S':
					next[0] += index;
					break;
				case 'N':
					next[0] -= index;
					break;
				case 'W':
					next[1] -= index;
					break;
			}
			if (range(next, park) && !obstacle(answer, next, park)) {
				answer[0] = next[0];
				answer[1] = next[1];
			}
		}

		return answer;
	}

	public boolean range(int[] next, String[] park) {
		int i = park.length;
		int j = park[0].length();

		return 0 <= next[0] && next[0] < i && 0 <= next[1] && next[1] < j;
	}

	public boolean obstacle(int[] current, int[] next, String[] park) {

		if (current[0] != next[0]) {
			for (int i = current[0]; i <= next[0]; i++) {
				if (park[i].charAt(current[1]) == 'X') {
					return true;
				}
			}
			for (int i = next[0]; i <= current[0]; i++) {
				if (park[i].charAt(current[1]) == 'X') {
					return true;
				}
			}
		}

		for (int i = current[1]; i <= next[1]; i++) {
			if (park[current[0]].charAt(i) == 'X') {
				return true;
			}
		}
		for (int i = next[1]; i <= current[1]; i++) {
			if (park[current[0]].charAt(i) == 'X') {
				return true;
			}
		}
		return false;
	}
}

public class Main {
	public static void main(String[] args) {
		String[] park = {"SOO", "OOO", "OOO"};
		String[] routes = {"E 2", "S 2", "W 1"};
		int[] result = new Solution().solution(park, routes);

		for (int i : result) {
			System.out.print(i + " ");
		}
	}
}
