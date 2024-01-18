package javasolve.level2.defensegame;

import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
	public int solution(int n, int k, int[] enemy) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		int answer = 0;

		while(answer < enemy.length) {
			n -= enemy[answer];
			pq.offer(enemy[answer]);

			if(n < 0 && k <= 0){
				break;
			} else if(n < 0) {
				while(n < 0 && k > 0 && !pq.isEmpty()) {
					n += pq.poll();
					k--;
				}
			}
			answer++;
		}
		return answer;
	}
}
public class Main {
	public static void main(String[] args) {
		int n = 7;
		int k = 3;
		int[] enemy = {4,2,4,5,3,3,1};
		int result = new Solution().solution(n,k,enemy);

		System.out.println("result = " + result);
	}
}
