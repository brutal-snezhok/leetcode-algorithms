package slidingWindow.hard;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/minimum-window-substring/description/
public class MinimumWindowSubstring_76 {
    public String minWindow(String s, String t) {
        // time O(n + m)
        // space O(n + m)

        if(s.length() < t.length())
            return "";

        Map<Character, Integer> countT = new HashMap<>();
        Map<Character, Integer> countWindow = new HashMap<>();

        // tMap will contains char -> count
        // example: t ="AABC"
        // tMap = {A -> 2, B -> 1, C -> 1}
        for(int i = 0; i < t.length(); i++) {
            countT.put(t.charAt(i), countT.getOrDefault(t.charAt(i), 0) + 1);
            //countWindow.put(s.charAt(i), countWindow.getOrDefault(s.charAt(i), 0) + 1);
        }

        int l = 0;
        int startInd = -1;
        int minL = Integer.MAX_VALUE;
        int matches = 0;
        for(int r = 0; r < s.length(); r++) {
            char ch = s.charAt(r);

            // update window map and "matches" variable, if char is in countT
            if(countT.containsKey(ch)) {
                countWindow.put(ch, countWindow.getOrDefault(ch, 0) + 1);
                if(countT.get(ch).equals(countWindow.get(ch)))
                    matches++;
            }

            // while two maps are the same, check how we can minimaze the length of window
            while(matches == countT.size()) {
                if(minL > (r - l + 1)) {
                    minL = r - l + 1;
                    startInd = l;
                }

                // if left char present in both maps, then need to check whether we should change matches var
                char leftCh = s.charAt(l);
                if(countWindow.containsKey(leftCh)) {
                    countWindow.put(leftCh, countWindow.get(leftCh) - 1);
                    if(countWindow.get(leftCh) < countT.get(leftCh))
                        matches--;
                }
                l++;
            }
        }

        return minL == Integer.MAX_VALUE ? "" : s.substring(startInd, startInd + minL);
    }
}
