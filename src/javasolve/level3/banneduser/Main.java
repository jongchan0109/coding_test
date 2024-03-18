package javasolve.level3.banneduser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    static boolean[] visited;
    static int n;
    static int bannedLen;
    static String[] userId;
    static String[] bannedId;
    static List<List<String>> result;

    public int solution(String[] user_id, String[] banned_id) {
        n = user_id.length;
        bannedLen = banned_id.length;
        visited = new boolean[n];
        result = new ArrayList<>();
        userId = user_id;
        bannedId = banned_id;

        factorial(0 , new String[bannedLen]);

        return result.size();
    }

    public void factorial(int depth, String[] fact) {

        if (depth == bannedLen) {


            for (int i = 0; i < bannedLen; i++) {
                if (!match(fact[i],bannedId[i])) {
                    return;
                }
            }

            Arrays.sort(fact);

            List<String> list = new ArrayList<>(Arrays.asList(fact).subList(0, bannedLen));


            if (!result.contains(list)) {
                result.add(list);
            }
            return;
        }


        for (int i = 0; i < n ; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            String[] copy = fact.clone();
            copy[depth] = userId[i];
            factorial(depth + 1, copy);
            visited[i] = false;

        }
    }

    public boolean match(String user, String ban) {
        if (user.length() != ban.length()) {
            return false;
        }

        for (int i = 0; i < user.length(); i++) {
            if (user.charAt(i) != ban.charAt(i) && ban.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }

}


public class Main {
    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "*rodo", "******", "******"};
        int result = new Solution().solution(user_id,banned_id);
        System.out.println("result = " + result);
    }
}
