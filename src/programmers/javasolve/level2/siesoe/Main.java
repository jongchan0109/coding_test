package programmers.javasolve.level2.siesoe;

class Solution {
	public long solution(int[] weights) {
		long answer = 0;

		int[] peoples = new int[1001];

		for (int weight : weights) {
			peoples[weight]++;
		}

		// 무게가 같은 경우
		for (int i = 100; i <= 1000; i++) {
			answer += ((long)peoples[i] * (peoples[i] - 1) / 2);
		}

		for (int i = 100; i < 1000; i++) {
			for (int j = i + 1; j <= 1000; j++) {
				if (3 * i == 2 * j || 2 * i == j || 4 * i == 3 * j) {
					answer +=  ((long)peoples[i] * peoples[j]);
				}
			}
		}

		return answer;
	}
}

public class Main {
	public static void main(String[] args) {
		int[] weights = {100, 100, 100, 150, 150, 200, 300};

		long answer = new Solution().solution(weights);
		System.out.println("answer = " + answer);
	}
}
