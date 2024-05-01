package programmers.javasolve.level1.pccpnum9;

class Solution {
    public boolean range(int n,int h,int w){
        return 0<=h && h<n && 0<=w && w<n;
    }
    public int solution(String[][] board, int h, int w) {
        int n = board.length;
        int count = 0;
        int[] dh = {-1,0,1,0};
        int[] dw = {0,-1,0,1};

        for(int i=0;i<4;i++){
            int nextH = h + dh[i];
            int nextW = w + dw[i];
            if(range(n,nextH,nextW)){
                if(board[h][w].equals(board[nextH][nextW]))
                    count++;
            }
        }
        return count;
    }
}

public class Main {
    public static void main(String[] args){
        String[][] board = {{"blue","red","orange","red"},{"red","red","blue","orange"},
                {"blue","orange","red","red"},{"orange","orange","red","blue"}};
        int h = 1;
        int w = 1;
        int result = new Solution().solution(board,h,w);
        System.out.println(result);
    }
}
