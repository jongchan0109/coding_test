package javasolve.level2.cantorbit;

class Solution {
	public int solution(int n, long l, long r) {
		int answer = 0;

		if(n==0){
			return 1;
		}

		for (long i = l; i <= r; i++) {
			if (i % 5 == 3) {
				continue;
			}
			long temp = i;
			while (temp != 0) {
				if (temp % 5 == 0) {
					temp /= 5;
				} else {
					temp = temp / 5 + 1;
				}
				if(temp%5==3){
					break;
				} else if(temp<5){
					answer++;
					break;
				}
			}
		}

		return answer;
	}
}

public class Main {
	public static void main(String[] args) {
		int n = 2;
		int l = 4;
		int r = 17;
		int result = new Solution().solution(n, l, r);
		System.out.println("result = " + result);
	}
}
