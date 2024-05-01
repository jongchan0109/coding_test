package programmers.javasolve.level2.numbertransport;

import java.util.*;

class Solution {
	public int solution(int x, int y, int n) {
		boolean[] visited = new boolean[y+1];
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{x,0});

		if(x==y){
			return 0;
		}

		while(!queue.isEmpty()){
			int[] current = queue.poll();

			int nextCount = current[1]+1;
			int nextNumber = current[0]+n;

			if(nextNumber == y){
				return nextCount;
			} else if(nextNumber < y){
				if(!visited[nextNumber]){
					visited[nextNumber] = true;
					queue.offer(new int[]{nextNumber,nextCount});
				}
			}
			nextNumber = current[0] * 2;
			if(nextNumber == y){
				return nextCount;
			} else if(nextNumber < y){
				if(!visited[nextNumber]){
					visited[nextNumber] = true;
					queue.offer(new int[]{nextNumber,nextCount});
				}
			}

			nextNumber = current[0] * 3;
			if(nextNumber == y){
				return nextCount;
			} else if(nextNumber < y){
				if(!visited[nextNumber]){
					visited[nextNumber] = true;
					queue.offer(new int[]{nextNumber,nextCount});
				}
			}

		}
		return -1;
	}
}

public class Main {
	public static void main(String[] args) {

		int x = 10;
		int y = 40;
		int n = 5;
		int result = new Solution().solution(x, y, n);

		System.out.println("result = " + result);
	}
}
