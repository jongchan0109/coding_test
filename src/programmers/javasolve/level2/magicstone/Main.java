package programmers.javasolve.level2.magicstone;


class Solution {
	public int solution(int storey) {
		int start = storey;
		int n = 0;
		int[] arr = new int[9];
		while(start!=0){
			arr[n] = start%10;
			n++;
			start/=10;
		}
		return calculate(arr,n,0,0);
	}

	public int calculate(int[] arr, int n, int index, int carry){
		if(index == n){
			if(carry ==1)
				return 1;
			return 0;
		}

		int current = arr[index] + carry;

		if(current == 10){
			return calculate(arr, n, index+1, 1);
		} else if(current == 0) {
			return calculate(arr,n,index+1,0);
		}

		int plus = 10 - current + calculate(arr,n,index+1,1);

		int minus = current + calculate(arr,n,index+1,0);

		return Math.min(plus,minus);
	}
}

public class Main {
	public static void main(String[] args) {
		int storey = 45;
		int result = new Solution().solution(storey);

		System.out.println("result = " + result);
	}
}
