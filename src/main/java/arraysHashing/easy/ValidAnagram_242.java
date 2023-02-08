package arraysHashing.easy;

import java.util.HashMap;

// https://leetcode.com/problems/valid-anagram/
public class ValidAnagram_242 {
    // s and t consist of lowercase English letters
    public boolean isAnagram(String s, String t) {
        // time O(n + m), n - length of s, m - length of t
        // space O(1)

        if (s.length() != t.length())
            return false;

        int[] map = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char chS = s.charAt(i);
            char chT = t.charAt(i);
            map[chS - 'a']++;
            map[chT - 'a']--;
        }

        for (int num : map) {
            if (num != 0)
                return false;
        }

        return true;
    }

    // What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
    public boolean isAnagram2(String s, String t) {
        // The hashtable should handle all unicode characters
        // time O(n), n - length of s
        // space O(n)

        if (s.length() != t.length())
            return false;

        HashMap<Character, Integer> smap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            smap.put(s.charAt(i), smap.getOrDefault(s.charAt(i), 0) + 1);
            smap.put(t.charAt(i), smap.getOrDefault(t.charAt(i), 0) - 1);
        }
        for (char c : smap.keySet()) {
            if (smap.get(c) != 0) {
                return false;
            }
        }

        return true;
    }
}
