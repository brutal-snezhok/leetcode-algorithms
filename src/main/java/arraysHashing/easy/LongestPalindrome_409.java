package arraysHashing.easy;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/longest-palindrome/description/
public class LongestPalindrome_409 {
    public int longestPalindrome(String s) {
        // time O(n)
        // space O(1), map has only 52 pairs

        // count num of every char
        // go through each char and add to res count / 2
        // if one of counts is odd then +1 to res

        Map<Character, Integer> countMap = new HashMap<>();
        for(char ch : s.toCharArray())
            countMap.put(ch, countMap.getOrDefault(ch, 0) + 1);

        int res = 0;
        boolean isAddOne = false;
        for(Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            if(!isAddOne)
                isAddOne = entry.getValue() % 2 != 0;

            res += entry.getValue() / 2;
        }
        res *= 2;

        return isAddOne ? res + 1 : res;
    }
}
