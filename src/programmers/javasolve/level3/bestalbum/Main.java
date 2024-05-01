package programmers.javasolve.level3.bestalbum;

import java.util.*;

class Solution {

    static class Album implements Comparable<Album> {
        int id;
        String genres;
        int plays;

        public Album(int id, String genres, int plays) {
            this.id = id;
            this.genres = genres;
            this.plays = plays;
        }

        @Override
        public int compareTo(Album other) {
            if (other.plays != this.plays) {
                return other.plays - this.plays;
            } else {
                return this.id - other.id;
            }
        }
    }

    public int[] solution(String[] genres, int[] plays) {

        HashMap<String, List<Album>> map = new HashMap<>();
        HashMap<String, Integer> sumMap = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            Album album = new Album(i, genres[i], plays[i]);
            map.computeIfAbsent(genres[i], k -> new ArrayList<>());
            sumMap.putIfAbsent(genres[i], 0);
            map.get(genres[i]).add(album);
            sumMap.put(genres[i], sumMap.get(genres[i]) + plays[i]);
        }

        Set<String> keySet = map.keySet();
        List<Integer> answer = new ArrayList<>();

        for (String key: keySet) {
            Collections.sort(map.get(key));
        }


        while (!keySet.isEmpty()) {
            int max = 0;
            String maxKey = null;

            for (String key : keySet) {
                if (sumMap.get(key) >= max) {
                    maxKey = key;
                    max = sumMap.get(key);
                }
            }
            if (maxKey != null) {
                List<Album> albums = map.get(maxKey);
                int count = 0;
                while (!albums.isEmpty() && count < 2) {
                    answer.add(albums.get(0).id);
                    albums.remove(0);
                    count++;
                }
                keySet.remove(maxKey);
            }

        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}

public class Main {
    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500,600,150,800,2500};
        int[] result = new Solution().solution(genres,plays);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
