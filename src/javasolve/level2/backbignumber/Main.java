package javasolve.level2.backbignumber;

import java.util.Stack;

class Solution {
	public int[] solution(int[] numbers) {
		int[] answer = new int[numbers.length];

		Stack<Integer> stack = new Stack<>();
		answer[numbers.length-1] = -1;
		stack.push(numbers[numbers.length-1]);

		for(int i = numbers.length-2; i>=0 ; i--){
			int num = -1;
			while(!stack.isEmpty()){
				if(numbers[i] < stack.peek()){
					num = stack.peek();
					break;
				} else{
					stack.pop();
				}
			}
			answer[i] = num;
			stack.push(numbers[i]);
		}
		return answer;
	}
}

public class Main {
	public static void main(String[] args) {
		int[] numbers = {2, 3, 3, 5};
		int[] result = new Solution().solution(numbers);

		for(int num: result){
			System.out.print(num+" ");
		}
	}
}
