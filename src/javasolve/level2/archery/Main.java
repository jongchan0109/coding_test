package javasolve.level2.archery;

import java.util.*;

class Solution {
    public int[] solution(int n, int[] info) {
        int[] lion = new int[11];
        List<int[]> result = new ArrayList<>();
        winAndLose(n, info, 0, lion, result);

        HashMap<Integer, int[]> resultMap = new HashMap<>();
        int maxCount = judge(info, result, resultMap);

        if (maxCount == 0) {
            return new int[]{-1};
        }
        return resultMap.get(maxCount);
    }

    public void winAndLose(int n, int[] info, int index, int[] lion, List<int[]> result) {
        if (index == 10) {
            if (n >= 0) {
                lion[index] = n;
                result.add(lion);
            }
            return;
        }
        int[] winLion = new int[11];
        int[] loseLion = new int[11];
        for (int i = 0; i < index; i++) {
            winLion[i] = lion[i];
            loseLion[i] = lion[i];
        }
        winLion[index] = info[index] + 1;
        loseLion[index] = 0;
        if (n - winLion[index] >= 0) {
            winAndLose(n - winLion[index], info, index + 1, winLion, result);
        }
        winAndLose(n, info, index + 1, loseLion, result);
    }

    public int judge(int[] info, List<int[]> result, HashMap<Integer, int[]> resultMap) {

        int maxCount = 0;

        for (int[] lion : result) {
            int count = grade(info, lion);
            if (count > 0) {
                if (count >= maxCount) {
                    maxCount = count;
                    if (resultMap.get(maxCount) != null) {
                        int[] rival = resultMap.get(maxCount);

                        if (!compare(rival, lion)) {
                            resultMap.put(maxCount, lion);
                        }
                    } else {
                        resultMap.put(maxCount, lion);
                    }
                }
            }
        }
        return maxCount;

    }

    public boolean compare(int[] rival, int[] lion) {
        for (int i = 10; i >= 0; i--) {
            if (lion[i] > rival[i]) {
                return false;
            }
        }
        return true;
    }

    public int grade(int[] info, int[] lion) {
        int peachSum = 0;
        int lionSum = 0;

        for (int i = 0; i < 11; i++) {
            if (lion[i] > info[i]) {
                lionSum += 10 - i;
            } else if (info[i] != 0) {
                peachSum += 10 - i;
            }
        }
        return lionSum - peachSum;
    }
}

public class Main {
    public static void main(String[] args) {
        int n = 9;
        int[] info = {0,0,1,2,0,1,1,1,1,1,1};
        int[] result = new Solution().solution(n, info);

        for (int i : result) {
            System.out.print(i + " ");
        }

    }
}
