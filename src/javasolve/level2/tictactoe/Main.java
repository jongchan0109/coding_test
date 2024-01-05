package javasolve.level2.tictactoe;

class Solution {

	static class Board{
		char[][] board;
		int oCount;
		int xCount;

		public Board(String[] board) {
			this.board = new char[3][3];
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					this.board[i][j] = board[i].charAt(j);
					if(this.board[i][j]=='O') {
						oCount++;
					}else if(this.board[i][j]=='X'){
						xCount++;
					}
				}
			}
		}

		public boolean win(char mode){
			boolean result = false;
			for(int i=0;i<3;i++){
				if(board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] == mode) {
					result = true;
				}
				if(board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] == mode) {
					result = true;
				}
			}
			if(board[1][1] == mode){
				if(board[0][0] == board[1][1] &&  board[1][1] == board[2][2])
					result = true;
				if(board[0][2] == board[1][1] && board[1][1] == board[2][0])
					result = true;
			}

			return result;
		}

		public int impossible(){
			if(xCount>oCount)
				return 0;
			if(Math.abs(xCount-oCount)>=2)
				return 0;
			if(win('O')){
				if(xCount == oCount){
					return 0;
				}
				if(win('X')){
					return 0;
				}
			}
			if(win('X')) {
				if (oCount > xCount) {
					return 0;
				}
			}
			return 1;
		}
	}
	public int solution(String[] board) {
		Board gameBoard = new Board(board);
		return gameBoard.impossible();
	}
}

public class Main {
	public static void main(String[] args) {
		String[] board = {"O.x",".O.","..X"};
		int answer = new Solution().solution(board);
		System.out.println("answer = " + answer);
	}
}
