package javasolve.level2.billiardpratice;

import java.util.ArrayList;
import java.util.List;

class Solution {

	public int distance(int x1, int y1, int x2, int y2) {
		return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
	}

	public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
		List<Integer> answerList = new ArrayList<>();

		for (int[] ball : balls) {
			int x = ball[0];
			int y = ball[1];

			int min = Integer.MAX_VALUE;
			for (int j = 0; j < 4; j++) {
				int nx = x;
				int ny = y;
				switch (j) {
					case 0:
						nx = 2 * m - x; // 우로 대칭
						if (y == startY && x > startX)
							continue;
						break;
					case 1:
						nx = -x; // 좌로 대칭
						if (y == startY && x < startX)
							continue;
						break;
					case 2:
						ny = 2 * n - y; // 위로 대칭
						if (x == startX && y > startY)
							continue;
						break;
					case 3:
						ny = -y; // 아래로 대칭
						if (x == startX && y < startY)
							continue;
				}

				min = Math.min(min, distance(startX, startY, nx, ny));
			}
			answerList.add(min);
		}

		// 거리의 제곱을 배열로 변환
		return answerList.stream().mapToInt(Integer::intValue).toArray();
	}
}

public class Main {
	public static void main(String[] args) {
		int m = 10;
		int n = 10;
		int startX = 3;
		int startY = 7;
		int[][] balls = {{7, 7}, {2, 7}, {7, 3}};
		int[] result = new Solution().solution(m, n, startX, startY, balls);

		for (int i : result) {
			System.out.println("result = " + i);
		}
	}
}
