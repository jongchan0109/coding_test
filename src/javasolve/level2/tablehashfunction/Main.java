package javasolve.level2.tablehashfunction;

class Solution {
	public int solution(int[][] data, int col, int row_begin, int row_end) {

		for(int i = 0 ; i < data.length -1; i++) {
			for (int j = i+1 ; j< data.length ; j++) {
				if(data[i][col-1] > data[j][col-1]) {
					int[] temp = data[i];
					data[i] = data[j];
					data[j] = temp;
				} else if(data[i][col-1] == data[j][col-1]) {
					if(data[i][0] < data[j][0]){
						int[] temp = data[i];
						data[i] = data[j];
						data[j] = temp;
					}
				}
			}
		}

		int s = 0;

		for (int j = 0; j < data[0].length; j++) {
			s += data[row_begin-1][j] % row_begin;
		}

		for (int i = row_begin; i < row_end; i++) {
			int sum = 0;
			for (int j = 0; j < data[0].length; j++) {
				sum += data[i][j] % (i+1);
			}
			s = s ^ sum;
		}

		return s;
	}
}

public class Main {
	public static void main(String[] args) {
		int[][] data = {{2, 2, 6}, {1, 5, 10}, {4, 2, 9}, {3, 8, 3}};
		int col = 2;
		int row_begin = 2;
		int row_end = 3;
		int result = new Solution().solution(data,col,row_begin,row_end);

		System.out.println("result = " + result);
	}
}
