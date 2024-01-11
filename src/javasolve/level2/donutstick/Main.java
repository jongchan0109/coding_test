package javasolve.level2.donutstick;

import java.util.HashMap;

class Solution {

	static class Vertex {
		int in;
		int out;

		public Vertex() {
			this.in = 0;
			this.out = 0;
		}
	}

	public int[] solution(int[][] edges) {
		int[] answer = {0, 0, 0, 0};

		HashMap<Integer, Vertex> map = new HashMap<>();

		int maxNum = 0;

		for (int[] edge : edges) {
			int from = edge[0];
			int to = edge[1];

			if (from > maxNum) {
				maxNum = from;
			}
			if (to > maxNum) {
				maxNum = to;
			}

			map.computeIfAbsent(from, k -> new Vertex());
			map.computeIfAbsent(to, k -> new Vertex());
			map.get(from).out++;
			map.get(to).in++;
		}

		for (int i = 1; i <= maxNum; i++) {
			if (map.get(i) == null)
				continue;

			Vertex vertex = map.get(i);

			if (vertex.in == 0 && vertex.out >= 2) {
				answer[0] = i;
			} else if (vertex.out == 0) {
				answer[2]++;
			} else if (vertex.in >= 2 && vertex.out >= 2) {
				answer[3]++;
			}
		}
		answer[1] = map.get(answer[0]).out - answer[2] - answer[3];

		return answer;
	}
}

public class Main {
	public static void main(String[] args) {
		int[][] edges = {{2, 3}, {4, 3}, {1, 1}, {2, 1}};
		int[] answer = new Solution().solution(edges);

		for (int i : answer) {
			System.out.println(i+" ");
		}

	}
}
