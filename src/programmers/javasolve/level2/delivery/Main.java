package programmers.javasolve.level2.delivery;

import java.util.Stack;

class Solution {
	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;

		Stack<Integer> deliveryStack = new Stack<>();
		Stack<Integer> pickupStack = new Stack<>();

		for (int i = 0; i < n; i++) {
			if (deliveries[i] != 0) {
				deliveryStack.push(i);
			}
			if (pickups[i] != 0) {
				pickupStack.push(i);
			}
		}

		int deliveryStorage = cap;
		int pickupStorage = cap;
		while (!deliveryStack.isEmpty() && !pickupStack.isEmpty()) {

			int longDistance = Math.max(deliveryStack.peek(), pickupStack.peek());
			answer += 2L * (longDistance + 1);

			while (deliveryStorage > 0 && !deliveryStack.isEmpty()) {
				int index = deliveryStack.peek();
				if (deliveries[index] <= deliveryStorage) {
					deliveryStorage -= deliveries[index];
					deliveryStack.pop();
				} else {
					deliveries[index] -= deliveryStorage;
					deliveryStorage = 0;
				}
			}
			while (pickupStorage > 0 && !pickupStack.isEmpty()) {
				int index = pickupStack.peek();
				if (pickups[index] <= pickupStorage) {
					pickupStorage -= pickups[index];
					pickupStack.pop();
				} else {
					pickups[index] -= pickupStorage;
					pickupStorage = 0;
				}
			}
			deliveryStorage = cap;
			pickupStorage = cap;
		}

		while (!deliveryStack.isEmpty()) {
			answer += 2L * (deliveryStack.peek() + 1);

			while (deliveryStorage > 0 && !deliveryStack.isEmpty()) {
				int index = deliveryStack.peek();
				if (deliveries[index] <= deliveryStorage) {
					deliveryStorage -= deliveries[index];
					deliveryStack.pop();
				} else {
					deliveries[index] -= deliveryStorage;
					deliveryStorage = 0;
				}

			}
			deliveryStorage = cap;
		}

		while (!pickupStack.isEmpty()) {
			answer += 2L * (pickupStack.peek() + 1);

			while (pickupStorage > 0 && !pickupStack.isEmpty()) {
				int index = pickupStack.peek();
				if (pickups[index] <= pickupStorage) {
					pickupStorage -= pickups[index];
					pickupStack.pop();
				} else {
					pickups[index] -= pickupStorage;
					pickupStorage = 0;
				}
			}
			pickupStorage = cap;
		}
		return answer;
	}
}

public class Main {
	public static void main(String[] args) {
		int cap = 4;
		int n = 5;
		int[] deliveries = {1,0,3,1,2};
		int[] pickups = {0,3,0,4,0};
		long result = new Solution().solution(cap,n,deliveries,pickups);

		System.out.println("result = " + result);
	}
}
