package graphs.hard;

import java.util.*;

// https://leetcode.com/problems/word-ladder/description/
public class WordLadder_127 {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // time O(m^2*n), m - length of each word, n - total number of words
        // space O(m^2*n)

        // main idea is to create adjacency list efficiently
        // {pattern, list of word of this patter}
        // example: {"d*g", [dog, dug, dag, dpg]}

        Map<String, List<String>> childrenMap = new HashMap<>(); // neighbors
        int length = beginWord.length();

        for(String word : wordList) {
            for(int j = 0; j < length; j++) {
                String pattern = word.substring(0, j) + "*" + word.substring(j + 1, length);
                List<String> children = childrenMap.getOrDefault(pattern, new ArrayList<>());
                children.add(word);
                childrenMap.put(pattern, children);
            }
        }

        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        visited.add(beginWord);
        int res = 1;

        while(!q.isEmpty()) {
            int n = q.size();
            for(int i = 0; i < n; i++) {
                String word = q.poll();

                if(endWord.equals(word))
                    return res;

                // take all children
                // first go through all wildcards that this word can have
                // then go by all adjacency lists of this wildcard
                for(int j = 0; j < length; j++) {
                    String pattern = word.substring(0, j) + "*" + word.substring(j + 1, length);
                    for(String child : childrenMap.getOrDefault(pattern, new ArrayList<>())) {
                        if(!visited.contains(child)) {
                            visited.add(child);
                            q.add(child);
                        }
                    }
                }
            }

            res++;
        }

        return 0;
    }

    public static void main(String[] args) {
        ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog"));
    }
}
