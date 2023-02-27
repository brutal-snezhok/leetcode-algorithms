package slidingWindow.medium;

// https://leetcode.com/problems/longest-repeating-character-replacement/description/
public class LongestRepeatingCharacterReplacement_424 {
    public int characterReplacement(String s, int k) {
        // time O(n)
        // space O(1)

        int[] countLetters = new int[26];
        int res = 0;
        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            char rightChar = s.charAt(r);
            countLetters[rightChar - 'A']++;

            int windowL = r - l + 1;
            int maxCount = getMaxCount(countLetters);
            if (windowL - maxCount > k) {
                char leftChar = s.charAt(l);
                l++;
                countLetters[leftChar - 'A']--;
            } else
                res = Math.max(res, windowL);
        }

        return res;
    }

    private int getMaxCount(int[] arr) {
        int res = 0;
        for (int num : arr)
            res = Math.max(res, num);

        return res;
    }
}
