package arraysHashing;

// https://leetcode.com/problems/longest-common-prefix/
public class LongestCommonPrefix_14 {
    public String longestCommonPrefix(String[] strs) {
        // time O(n * m), n - number of string, m - avg length of string
        // space O(1)

        if(strs.length == 0)
            return "";

        // go through first word
        // and check if first letter is the same in rest words, then go to next letter else return prefix

        for(int i = 0; i < strs[0].length(); i++) {
            for(int j = 1; j < strs.length; j++) {
                String s = strs[j];
                if(i >= s.length() || strs[0].charAt(i) != s.charAt(i))
                    return strs[0].substring(0, i);
            }
        }

        return strs[0];
    }
}
