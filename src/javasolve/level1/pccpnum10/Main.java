package javasolve.level1.pccpnum10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {

    public int modeSelect(String str){
        switch (str) {
            case "code":
                return 0;
            case "date":
                return 1;
            case "maximum":
                return 2;
            default:
                return 3;
        }
    }
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        List<int[]> list = new ArrayList<>();
        int extMode = modeSelect(ext);
        int sortByMode = modeSelect(sort_by);

        for (int[] datum : data) {
            if (datum[extMode] < val_ext) {
                list.add(datum);
            }
        }
        list.sort(Comparator.comparingInt(arr -> arr[sortByMode]));

        int[][] result = new int[list.size()][];
        for(int i=0;i<list.size();i++){
            result[i] = list.get(i);
        }
        return result;
    }
}

public class Main {
    public static void main(String[] args){
        int[][] data = {{1,20300104,100,80},{2,20300804,847,37},{3,20300401,10,8}};
        String ext = "date";
        int valExt = 20300501;
        String sortBy = "remain";
        int[][] result = new Solution().solution(data,ext,valExt,sortBy);

        for(int[] row : result){
            for(int col: row){
                System.out.print(col+" ");
            }
            System.out.println();
        }
    }
}
