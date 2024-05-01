package programmers.javasolve.level1.memoryscore;


import java.util.HashMap;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        HashMap<String, Integer> map = new HashMap<>();

        for(int i=0;i<name.length;i++){
            map.put(name[i],yearning[i]);
        }

        int[] answer = new int[photo.length];

        for(int i=0;i< photo.length;i++){
            int sum = 0;
            for(int j=0;j<photo[i].length;j++){
                if(map.containsKey(photo[i][j])){
                    sum += map.get(photo[i][j]);
                }
            }
            answer[i] = sum;
        }
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        String[] name = {"may","kein","kain","radi"};
        int[] yearning = {5,10,1,3};
        String[][] photo = {{"may","kein","kain","radi"},
                {"may","kein","brin","deny"},
                {"kon","kain","may","coni"}};
        int[] answer = new Solution().solution(name,yearning,photo);

        for(int result: answer){
            System.out.print(result+" ");
        }
    }
}
