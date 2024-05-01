package programmers.javasolve.level2.dungeons;

class Solution {
    public int solution(int k, int[][] dungeons) {
        int n = dungeons.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return permutation(k, arr, 0, n, dungeons);
    }


    public int permutation(int k, int[] arr, int depth, int n, int[][] dungeons) {
        int max = 0;
        if (depth == n - 1) {
            return countDungeon(k, n, arr, dungeons);
        }

        for (int i = depth; i < n; i++) {
            swap(arr, depth, i);
            int count = permutation(k,arr, depth + 1, n, dungeons);
            swap(arr, depth, i);
            max = Math.max(count,max);
        }
        return max;
    }

    public int countDungeon(int k, int n, int[] arr, int[][] dungeons) {
        int answer = 0;

        while (answer != n) {
            if (k >= dungeons[arr[answer]][0]) {
                k -= dungeons[arr[answer]][1];
            } else {
                break;
            }
            answer++;
        }
        return answer;
    }

    public void swap(int[] arr, int depth, int i) {
        int temp = arr[i];
        arr[i] = arr[depth];
        arr[depth] = temp;
    }
}

public class Main {
    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
        int result = new Solution().solution(k, dungeons);

        System.out.println("result = " + result);
    }
}
