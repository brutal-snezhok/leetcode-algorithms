package arraysHashing;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/word-pattern/description/
public class WordPattern_290 {
    public boolean wordPattern(String pattern, String s) {
        // time O(n + m)
        // space O(n + m)

        // init two maps: char -> string, string -> char
        // if map1 contains string for curr char then compare it with curr string
        // if map2 contains curr string then compare char from map2 with curr char

        String[] strs = s.split(" ");
        if(pattern.length() != strs.length)
            return false;

        Map<Character, String> charToStringMap = new HashMap<>(); // a -> dog, b -> cat
        Map<String, Character> stringToCharMap = new HashMap<>();

        for(int i = 0; i < pattern.length(); i++) {
            char currChar = pattern.charAt(i);
            String currString = strs[i];

            if(charToStringMap.containsKey(currChar) && !charToStringMap.get(currChar).equals(currString))
                return false;
            if(stringToCharMap.containsKey(currString) && stringToCharMap.get(currString) != currChar)
                return false;

            charToStringMap.put(currChar, currString);
            stringToCharMap.put(currString, currChar);
        }

        return true;
    }
}
