package programmers.javasolve.level3.wallinspection;

class Solution {

    static int[] weakAppend;
    static int answer;
    static boolean[] visited;
    public int solution(int n, int[] weak, int[] dist) {
        answer = Integer.MAX_VALUE;
        weakAppend = new int[weak.length * 2];
        visited = new boolean[dist.length];

        for (int i = 0; i < weak.length; i++) {
            weakAppend[i] = weak[i];
            weakAppend[i + weak.length] = weak[i] + n;
        }

        for (int i = 0; i < weak.length; i++) {
            DFS(i,0,dist, new int[dist.length]);
        }

        if (answer == Integer.MAX_VALUE) {
            return -1;
        }
        return answer;
    }

    private void DFS(int start, int depth, int[] dist, int[] friends) {

        if (depth == dist.length) {
            answer = Math.min(answer,inspect(start, start + weakAppend.length / 2, friends));
            return;
        }

        for (int i = 0; i < dist.length;i++) {
            if (visited[i]) {
                continue;
            }
            friends[depth] = dist[i];
            visited[i] = true;
            DFS(start, depth+1, dist, friends);
            visited[i] = false;
        }
    }

    private int inspect(int start ,int end, int[] friends) {
        int people = 1;

        int pos = weakAppend[start] + friends[people - 1];

        for (int i = start; i < end; i++) {
            if (pos < weakAppend[i]) {
                people++;
                if (people > friends.length) {
                    return Integer.MAX_VALUE;
                }
                pos = weakAppend[i] + friends[people - 1];
            }
        }
        return people;
    }

}

public class Main {
    public static void main(String[] args) {
        int n = 12;
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};
        int result = new Solution().solution(n, weak, dist);
        System.out.println("result = " + result);
    }
}