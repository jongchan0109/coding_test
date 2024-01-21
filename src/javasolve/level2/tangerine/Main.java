package javasolve.level2.tangerine;

import java.util.HashMap;
import java.util.PriorityQueue;

class Solution {
	public int solution(int k, int[] tangerine) {
		int answer = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));
		for (int j : tangerine) {
			map.merge(j, 1, Integer::sum);
		}
		for(int key: map.keySet()) {
			priorityQueue.offer(new int[] {key, map.get(key)});
		}

		while(!priorityQueue.isEmpty()) {
			int current = priorityQueue.poll()[1];
			k-=current;
			answer++;
			if(k<=0) {
				break;
			}
		}
		return answer;
	}
}

public class Main {
	public static void main(String[] args) {
		int k = 6;
		int[] tangerine = {1,3,2,5,4,5,2,3};
		int result = new Solution().solution(k,tangerine);

		System.out.println("result = " + result);
	}
}
