package programmers.javasolve.level2.emoticondiscount;

class Solution {

	public int[] discount = {10, 20, 30, 40};
	public int[] permutations;

	public int[] DFS(int index, int[] emoticons, int[][] users) {
		if (index == emoticons.length) {
			int service = 0;
			int totalPrice = 0;

			for (int[] user : users) {
				int sum = 0;
				for (int j = 0; j < emoticons.length; j++) {
					if (permutations[j] >= user[0]) {
						sum += emoticons[j] * (100 - permutations[j]) / 100;
					}
				}
				if (sum >= user[1]) {
					service++;
				} else {
					totalPrice += sum;
				}
			}
			return new int[] {service, totalPrice};
		}
		int[] max = {0,0};

		for (int j : discount) {
			permutations[index] = j;
			int[] result = DFS(index + 1, emoticons, users);
			if(result[0] > max[0] || result[0] == max[0] && result[1] >= max[1]) {
				max[0] = result[0];
				max[1] = result[1];
			}
		}
		return max;
	}

	public int[] solution(int[][] users, int[] emoticons) {
		permutations = new int[emoticons.length];
		return DFS(0,emoticons,users);
	}
}

public class Main {
	public static void main(String[] args) {
		int[][] users = {{40, 10000}, {25, 10000}};
		int[] emoticons = {7000, 9000};

		int[] result = new Solution().solution(users, emoticons);

		for (int i : result) {
			System.out.print(i + " ");
		}
	}
}
