package programmers.javasolve.level2.pccpnum2;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    static class Oil{
        int quantity;
        public Oil(int quantity) {
            this.quantity = quantity;
        }
    }

    Oil[][] oils;
    boolean[][] visited;
    int row, col;
    int[] di = {0, 1, 0, -1};
    int[] dj = {1, 0, -1, 0};

    boolean range(int i, int j) {
        return 0 <= i && i < row && 0 <= j && j < col;
    }

    void BFS(int[][] land) {
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(!visited[i][j] && land[i][j]==1){
                    int count = 0;
                    Queue<int[]> queue = new LinkedList<>();
                    Queue<int[]> cpy = new LinkedList<>();
                    visited[i][j] = true;
                    queue.offer(new int[]{i,j});

                    while(!queue.isEmpty()){
                        int[] current = queue.poll();
                        cpy.offer(current);
                        for(int d=0;d<4;d++){
                            int nextI = current[0]+di[d];
                            int nextJ = current[1] + dj[d];
                            if(range(nextI,nextJ) && !visited[nextI][nextJ] && land[nextI][nextJ]==1){
                                visited[nextI][nextJ] = true;
                                queue.offer(new int[] {nextI,nextJ});
                            }
                        }
                        count++;
                    }
                    Oil oil = new Oil(count);
                    while(!cpy.isEmpty()){
                        int[] current = cpy.poll();
                        oils[current[0]][current[1]] = oil;
                    }
                }
                visited[i][j] = true;
            }
        }
    }

    public int solution(int[][] land) {
        row = land.length;
        col = land[0].length;
        visited = new boolean[row][col];
        oils = new Oil[row][col];
        BFS(land);
        int max = 0;
        for(int j=0;j<col;j++){
            List<Oil> list = new ArrayList<>();
            int sum = 0;
            for(int i=0;i<row;i++){
                if(oils[i][j] != null){
                    if(!list.contains(oils[i][j])){
                        list.add(oils[i][j]);
                    }
                }
            }
            for (Oil oil : list) {
                sum += oil.quantity;
            }
            max = Math.max(sum,max);
        }
        return max;
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] land = {{0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0},
                {1, 1, 0, 0, 0, 1, 1, 0},
                {1, 1, 1, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 1, 1}};

        int answer = new Solution().solution(land);
        System.out.println("answer = " + answer);
    }
}
