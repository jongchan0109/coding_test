package javasolve.level2.point;

class Solution {
	public long solution(int k, int d) {
		long answer = 0L;

		for (int x = 0; x <= d; x += k) {
			double y = Math.sqrt(Math.pow(d, 2) - Math.pow(x, 2));
			answer += (long)(Math.floor(y) / k +1);
		}
		return answer;
	}
}

public class Main {
	public static void main(String[] args) {
		int k = 2;
		int d = 4;
		long result = new Solution().solution(k, d);

		System.out.println("result = " + result);
	}
}
