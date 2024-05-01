package programmers.javasolve.level3.sheepandwolf;

import java.util.*;

class Solution {

    static Map<Integer, List<Integer>> children;
    static int[] information;
    static int maxSheepCnt = 0;

    int solution(int[] info, int[][] edges) {
        information = info;
        children = new HashMap<>();

        for (int[] l : edges) {
            int parent = l[0];
            int child = l[1];
            children.computeIfAbsent(parent, k -> new ArrayList<>());
            children.get(parent).add(child);
        }

        List<Integer> list = new ArrayList<>();
        list.add(0);
        dfs(0, 0, 0, list);
        return maxSheepCnt;
    }

    private static void dfs(int idx, int sheepCnt, int wolfCnt, List<Integer> nextPos) {

        if (information[idx] == 0) sheepCnt++;
        else wolfCnt++;

        if (wolfCnt >= sheepCnt) return;
        maxSheepCnt = Math.max(sheepCnt, maxSheepCnt);

        // 다음 탐색 위치 갱신
        List<Integer> list = new ArrayList<>(nextPos);

        // 다음 탐색 목록중 현재 위치제외
        list.remove(Integer.valueOf(idx));
        if (children.get(idx) != null) {
            list.addAll(children.get(idx));
        }

        for (int next : list) {
            dfs(next, sheepCnt, wolfCnt, list);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
        int[][] edges = {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};

        int result = new Solution().solution(info, edges);
        System.out.println("result = " + result);
    }
}
