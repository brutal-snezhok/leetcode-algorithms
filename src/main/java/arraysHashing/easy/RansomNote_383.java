package arraysHashing.easy;

// https://leetcode.com/problems/ransom-note/description/
public class RansomNote_383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        // time O(n + m)
        // space O(1)

        int[] countMap = new int[26];
        for(char ch : magazine.toCharArray())
            countMap[ch - 'a']++;

        for(char ch : ransomNote.toCharArray()) {
            int val = countMap[ch - 'a'];
            if(val > 0)
                countMap[ch - 'a']--;
            else
                return false;
        }

        return true;
    }
}
