package programmers.javasolve.level3.triproute;

import java.util.*;

class Solution {

    List<String> routes;
    static boolean[] visited;

    public String[] solution(String[][] tickets) {

        visited = new boolean[tickets.length];
        routes = new ArrayList<>();
        DFS("ICN", "ICN", tickets, 0);

        Collections.sort(routes);

        return routes.get(0).split(" ");
    }

    private void DFS(String start, String route, String[][] tickets, int depth) {
        if (depth == tickets.length) {
            routes.add(route);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (start.equals(tickets[i][0]) && !visited[i]) {
                visited[i] = true;
                DFS(tickets[i][1], route + " " + tickets[i][1], tickets, depth + 1);
                visited[i] = false;
            }
        }

    }

}

public class Main {
    public static void main(String[] args) {
        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[] result = new Solution().solution(tickets);
        for (String s : result) {
            System.out.println("s = " + s);
        }
    }
}
