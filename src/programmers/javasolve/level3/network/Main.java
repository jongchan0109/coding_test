package programmers.javasolve.level3.network;

class Solution {

    static int answer;
    static boolean[] visited;
    static int N;

    public int solution(int n, int[][] computers) {
        answer = n;
        visited = new boolean[n];
        N = n;

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            DFS(i, computers);
        }

        return answer;
    }

    public void DFS(int from, int[][] computers) {

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            if (computers[from][i] == 1) {
                visited[i] = true;
                answer--;
                DFS(i, computers);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int n = 3;
        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int result = new Solution().solution(n, computers);
        System.out.println("result = " + result);
    }
}
