package slidingWindow.medium;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
public class LongestSubstringWithoutRepeatingCharacters_3 {
    public int lengthOfLongestSubstring(String s) {
        // time O(n)
        // space O(1), unique characters do not depend on length of string

        /**
         1. set will have unique chars of substring
         2. once we meet char that is already in set, increase left pointer until set contains this char
         */

        Set<Character> uniqueChars = new HashSet<>();
        int l = 0;
        int res = 0;
        for(int r = 0; r < s.length(); r++) {
            char chR = s.charAt(r);
            while(uniqueChars.contains(chR) && l < r) {
                char charL = s.charAt(l);
                uniqueChars.remove(charL);
                l++;
            }

            uniqueChars.add(chR);
            res = Math.max(res, r - l + 1);
        }

        return res;
    }
}
