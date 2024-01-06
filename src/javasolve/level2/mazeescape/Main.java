package javasolve.level2.mazeescape;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
	int[] di = {0, 1, 0, -1};
	int[] dj = {1, 0, -1, 0};

	static class Point {
		int i;
		int j;
		int count;

		public Point(int i, int j, int count) {
			this.i = i;
			this.j = j;
			this.count = count;
		}
	}

	public int solution(String[] maps) {
		Point start = new Point(0, 0, 0);
		Point lever = new Point(0, 0, 0);
		Point escape = new Point(0, 0, 0);


		for (int i = 0; i < maps.length; i++) {
			for (int j = 0; j < maps[i].length(); j++) {
				if (maps[i].charAt(j) == 'S') {
					start.i = i;
					start.j = j;
				} else if (maps[i].charAt(j) == 'L') {
					lever.i = i;
					lever.j = j;
				} else if (maps[i].charAt(j) == 'E') {
					escape.i = i;
					escape.j = j;
				}
			}
		}
		int answer1 = BFS(maps, start, lever);
		int answer2 = BFS(maps, lever, escape);

		if (answer1 == 0 || answer2 == 0)
			return -1;
		return answer1 + answer2;
	}

	private int BFS(String[] maps, Point start, Point end) {
		int count = 0;
		Queue<Point> queue = new LinkedList<>();
		queue.offer(start);
		boolean[][] visited = new boolean[maps.length][maps[0].length()];
		visited[start.i][start.j] = true;

		while (!queue.isEmpty()) {
			Point current = queue.poll();
			if (current.i == end.i && current.j == end.j) {
				count = current.count;
				break;
			}
			for (int i = 0; i < 4; i++) {
				int ni = current.i + di[i];
				int nj = current.j + dj[i];
				if (0 <= ni && ni < maps.length && 0 <= nj && nj < maps[0].length()) {
					if (!visited[ni][nj] && maps[ni].charAt(nj) != 'X') {
						queue.offer(new Point(ni, nj, current.count + 1));
						visited[ni][nj] = true;
					}
				}
			}
		}
		return count;
	}
}

public class Main {
	public static void main(String[] args) {
		String[] maps = {"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"};
		int answer = new Solution().solution(maps);

		System.out.println("answer = " + answer);
	}
}
