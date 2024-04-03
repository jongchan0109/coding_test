package javasolve.level3.routesearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    public int[][] solution(int[][] nodeInfo) {
        int n = nodeInfo.length;
        Node[] graph = new Node[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new Node(nodeInfo[i][0], nodeInfo[i][1], i + 1);
        }

        Arrays.sort(graph);
        List<Integer> prefix = new ArrayList<>();
        List<Integer> postfix = new ArrayList<>();
        search(graph, 0, n - 1, prefix, postfix);

        int[] pre = prefix.stream().mapToInt(Integer::intValue).toArray();
        int[] post = postfix.stream().mapToInt(Integer::intValue).toArray();

        return new int[][]{pre, post};
    }

    void search(Node[] graph, int left, int right, List<Integer> prefix, List<Integer> postfix) {
        if (left > right) {  // 왼쪽 인덱스가 오른쪽 인덱스보다 큰 경우 종료
            return;
        }

        int max = -1;
        int maxIdx = -1;

        for (int i = left; i <= right; i++) {
            if (graph[i].y > max) {
                max = graph[i].y;
                maxIdx = i;
            }
        }
        prefix.add(graph[maxIdx].index);
        search(graph, left, maxIdx - 1, prefix, postfix);  // 왼쪽 구간 재귀 호출 시 인덱스 범위 수정
        search(graph, maxIdx + 1, right, prefix, postfix);
        postfix.add(graph[maxIdx].index);
    }


    static class Node implements Comparable<Node> {
        int x;
        int y;
        int index;

        Node(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            return this.x - o.x;
        }
    }
}


public class Main {
    public static void main(String[] args) {
        int[][] nodeInfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        int[][] result = new Solution().solution(nodeInfo);
        for (int[] node : result) {
            System.out.println(Arrays.toString(node));
        }
    }
}
