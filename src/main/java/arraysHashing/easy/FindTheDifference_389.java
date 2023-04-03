package arraysHashing.easy;

// https://leetcode.com/problems/find-the-difference/description/
public class FindTheDifference_389 {
    public char findTheDifference(String s, String t) {
        // time O(n)
        // space O(1)

        if(s.length() == 0)
            return t.charAt(0);

        int[] counts = new int[26];
        for(int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            counts[t.charAt(i) - 'a']--;
        }
        counts[t.charAt(t.length() - 1) - 'a']--;

        for(int i = 0; i < 26; i++)
            if(counts[i] != 0)
                return (char)('a' + i);

        return '0'; // from task it is unreacheable, so you can return any character
    }
}
