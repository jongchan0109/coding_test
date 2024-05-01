package programmers.javasolve.level2.mining;

class Solution {

    public int DFS(int pick, int dia, int iron, int stone, int index, String[] minerals) {
        int result = 0;

        if(pick == 0){
            dia--;
        } else if(pick == 1){
            iron--;
        } else{
            stone--;
        }


        if (dia < 0 || iron < 0 ||  stone < 0)
            return Integer.MAX_VALUE;

        int last = Math.min(index + 5, minerals.length);

        for (int i = index; i < last; i++) {
            switch (pick) {
                case 0:
                    result += 1;
                    break;
                case 1:
                    if (minerals[i].equals("diamond")) {
                        result += 5;
                    } else {
                        result += 1;
                    }
                    break;
                case 2:
                    if (minerals[i].equals("diamond")) {
                        result += 25;
                    } else if (minerals[i].equals("iron")) {
                        result += 5;
                    } else {
                        result += 1;
                    }
            }
        }

        if(last == minerals.length || dia + iron + stone == 0){
            return result;
        }

        return result + Math.min(DFS(0,dia,iron,stone,index+5,minerals),
                Math.min(DFS(1,dia,iron,stone,index+5,minerals),
                        DFS(2,dia,iron,stone,index+5,minerals)));
    }

    public int solution(int[] picks, String[] minerals) {
        int answer = Integer.MAX_VALUE;
        for(int i=0 ; i < 3 ; i++){
            answer = Math.min(DFS(i,picks[0],picks[1],picks[2],0,minerals),answer);
        }
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] picks = {1, 3, 2};
        String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
        int result = new Solution().solution(picks, minerals);

        System.out.println("result = " + result);
    }
}
