package backtracking.medium;

import java.util.List;

// https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
public class MaximumLengthOfConcatenatedStringWithUniqueCharacters_1239 {
    int max = 0;

    public int maxLength(List<String> arr) {
        // optimized with StringBuilder
        // O(2^n)
        // space O(n)

        backtracking(arr, 0, new StringBuilder());
        return max;
    }

    private void backtracking(List<String> arr, int ind, StringBuilder curr) {
        boolean isUniq = isUniq(curr);
        if (isUniq)
            max = Math.max(max, curr.length());
        if (ind == arr.size() || !isUniq)
            return;

        for (int i = ind; i < arr.size(); i++) {
            int length = curr.length();
            curr.append(arr.get(i));

            backtracking(arr, i + 1, curr);

            curr.setLength(length);
        }
    }

    private boolean isUniq(StringBuilder s) {
        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++)
            chars[s.charAt(i) - 'a']++;

        for (int i = 0; i < 26; i++) {
            if (chars[i] > 1)
                return false;
        }

        return true;
    }
}
