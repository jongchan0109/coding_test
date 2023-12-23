package java_solve.level1.pccp_num1;

import java.util.HashMap;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        int time = 0;
        int hp = health;
        int bandTime = 0;
        int lastTime = attacks[attacks.length-1][0];
        HashMap<Integer,Integer> map = new HashMap<>();

        for(int[] attack: attacks){
            map.put(attack[0],attack[1]);
        }

        while(hp>0 && time<lastTime){
            time++;

            if(map.containsKey(time)){
                hp = hp - map.get(time);
                bandTime = 0;
            } else{
                bandTime++;
                if(hp+bandage[1]<health){
                    hp += bandage[1];
                }else{
                    hp = health;
                }
                if(bandTime == bandage[0]){
                    if(hp+bandage[2]<=health) {
                        hp += bandage[2];
                    } else{
                        hp = health;
                    }
                    bandTime = 0;
                }

            }
            System.out.println("time = "+ time+" hp = "+hp);

        }
        answer = hp;
        if(answer<=0)
            return -1;
        return answer;
    }
}


public class Main {
    public static void main(String[] args){
        int[] bandage = {5,1,5};
        int health = 30;
        int[][] attacks = {{2,10},{9,15},{10,5},{11,5}};
        int result = new Solution().solution(bandage,health,attacks);

        System.out.println(result);
    }
}
