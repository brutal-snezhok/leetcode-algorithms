package slidingWindow.medium;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
public class FindAllAnagramsInString_438 {
    // solution1
    public List<Integer> findAnagrams1(String s2, String s1) {
        // time O(s2.length + s1.length)
        // space O(1)

        int[] s1Count = new int[26];
        for(char ch : s1.toCharArray())
            s1Count[ch - 'a']++;

        int[] windowCount = new int[26];
        List<Integer> res = new ArrayList<>();

        int l = 0;
        for(int r = 0; r < s2.length(); r++) {
            char ch = s2.charAt(r);
            windowCount[ch - 'a']++;

            if(r >= s1.length()) {
                char chL = s2.charAt(l);
                windowCount[chL - 'a']--;
                l++;
            }

            if(r - l + 1 == s1.length() && isTheSame(s1Count, windowCount))
                res.add(l);
        }

        return res;
    }

    private boolean isTheSame(int[] sMap, int[] pMap) {
        for(int i = 0; i < 26; i++) {
            if(sMap[i] != pMap[i])
                return false;
        }

        return true;
    }

    // solution2
    public List<Integer> findAnagrams2(String s, String p) {
        // time O(s + p)
        // space O(1)

        List<Integer> res = new ArrayList<>();
        if(p.length() > s.length())
            return res;

        int[] sMap = new int[26];
        int[] pMap = new int[26];
        for(int i = 0; i < p.length(); i++) {
            sMap[s.charAt(i) - 'a']++;
            pMap[p.charAt(i) - 'a']++;
        }

        if(isTheSame(sMap, pMap))
            res.add(0);

        int l = 0;
        for(int i = p.length(); i < s.length(); i++) {
            char addCh = s.charAt(i);
            char removeCh = s.charAt(l++);
            sMap[addCh - 'a']++;
            sMap[removeCh - 'a']--;

            if(isTheSame(sMap, pMap))
                res.add(l);
        }


        return res;
    }
}
