package javasolve.level3.wordchange;

import java.util.*;

class Solution {

    static class Word {
        String word;
        int count;

        public Word(String word, int count) {
            this.word = word;
            this.count = count;
        }

    }

    public int solution(String begin, String target, String[] words) {
        return BFS(begin, target, words);
    }

    private int BFS(String begin, String target, String[] words) {
        Queue<Word> queue = new LinkedList<>();
        queue.add(new Word(begin, 0));
        boolean[] visited = new boolean[words.length];

        while (!queue.isEmpty()) {
            Word current = queue.poll();
            if (current.word.equals(target)) {
                return current.count;
            }
            for (int i = 0; i < visited.length; i++) {
                if (!visited[i] && canConvert(current.word, words[i])) {
                    queue.add(new Word(words[i], current.count + 1));
                    visited[i] = true;
                }
            }

        }

        return 0;

    }

    private boolean canConvert(String current, String convert) {
        int count = 0;
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) != convert.charAt(i)) {
                count++;
            }
        }
        return count == 1;
    }
}


public class Main {
    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        int result = new Solution().solution(begin, target, words);
        System.out.println("result = " + result);
    }
}
