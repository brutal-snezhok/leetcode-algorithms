package arraysHashing.string;

import java.util.*;

// https://leetcode.com/problems/group-anagrams/description/
public class GroupAnagrams_49 {
    // solution1
    public List<List<String>> groupAnagrams1(String[] strs) {
        // sorting
        // time O(n*klogk), n -number of words, k - avg length of word
        // space O(n*k)

        Map<String, List<String>> map = new HashMap<>();
        for(String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);

            String key = String.valueOf(chars);
            map.computeIfAbsent(key, v -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(map.values());
    }
    // solution2
    public List<List<String>> groupAnagrams2(String[] strs) {
        // time O(n*k), n -number of words, k - avg length of word
        // space O(n*k)

        Map<String, List<String>> map = new HashMap<>();
        for(String s : strs) {
            char[] count = new char[26];

            for(char ch : s.toCharArray()) {
                count[ch - 'a']++;
            }

            String key = new String(count);
            map.computeIfAbsent(key, v -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(map.values());
    }

    // solution3
    public List<List<String>> groupAnagrams3(String[] strs) {
        // time O(n*k), n - number of words, k - avg length of word
        // space O(n*k)

        Map<String, List<String>> map = new HashMap<>();
        for(String s : strs) {
            int[] count = new int[26];

            for(char ch : s.toCharArray()) {
                count[ch - 'a']++;
            }

            StringBuilder build = new StringBuilder();
            for(int i = 0; i < 26; i++)
                build.append(count[i] + "#");

            String key = new String(build);
            map.computeIfAbsent(key, v -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(map.values());
    }
}
