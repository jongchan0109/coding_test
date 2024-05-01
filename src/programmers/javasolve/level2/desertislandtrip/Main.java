package programmers.javasolve.level2.desertislandtrip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
	int[] di = {0, 1, 0, -1};
	int[] dj = {1, 0, -1, 0};

	public int[] solution(String[] maps) {
		List<Integer> list = new ArrayList<>();
		boolean[][] visited = new boolean[maps.length][maps[0].length()];

		for (int i = 0; i < maps.length; i++) {
			for (int j = 0; j < maps[0].length(); j++) {
				if (maps[i].charAt(j) == 'X' || visited[i][j]) {
					visited[i][j] = true;
					continue;
				}
				Queue<int[]> queue = new LinkedList<>();
				int sum = 0;
				visited[i][j] = true;
				queue.offer(new int[] {i, j});

				while (!queue.isEmpty()) {
					int[] current = queue.poll();
					sum += maps[current[0]].charAt(current[1]) - '0';

					for (int d = 0; d < 4; d++) {
						int nextI = current[0] + di[d];
						int nextJ = current[1] + dj[d];

						if (range(nextI, nextJ, maps) && !visited[nextI][nextJ] && maps[nextI].charAt(nextJ)!='X') {
							visited[nextI][nextJ] = true;
							queue.offer(new int[] {nextI, nextJ});
						}
					}
				}
				list.add(sum);
			}
		}
		if(list.isEmpty())
			return new int[] {-1};
		int[] answer = new int[list.size()];
		for(int i=0;i<list.size();i++){
			answer[i] = list.get(i);
		}
		Arrays.sort(answer);
		return answer;
	}

	private boolean range(int nextI, int nextJ, String[] maps) {
		int i = maps.length;
		int j = maps[0].length();

		return 0 <= nextI && nextI < i && 0 <= nextJ && nextJ < j;
	}

}

public class Main {
	public static void main(String[] args) {
		String[] maps = {"X591X", "X1X5X", "X231X", "1XXX1"};
		int[] answer = new Solution().solution(maps);

		for (int i : answer) {
			System.out.print(i + " ");
		}
	}
}
