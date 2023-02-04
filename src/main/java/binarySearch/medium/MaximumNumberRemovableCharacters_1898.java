package binarySearch.medium;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/maximum-number-of-removable-characters/description/
public class MaximumNumberRemovableCharacters_1898 {
    public int maximumRemovals(String s, String p, int[] removable) {
        // time O(nlogm), n - length of s, m - length of removable
        // space O(m)

        // do binary search for removable arr
        // fina mid and take all values before mid
        // compare two strings s and p and remove from comparing characters by indexes from removable

        int l = 0;
        int r = removable.length - 1;
        int res = 0;
        while(l <= r) {
            int mid = l + (r - l) / 2;

            // put all indexes before mid inclusively into set
            Set<Integer> currRemovable = new HashSet<>();
            for(int i = 0; i <= mid; i++)
                currRemovable.add(removable[i]);

            if(isSubstring(s, p, currRemovable)) {
                res = Math.max(res, mid + 1);
                l = mid + 1;
            }
            else
                r = mid - 1;
        }

        return res;
    }

    private boolean isSubstring(String s, String p, Set<Integer> excludedIndexes) {
        int p1 = 0;
        int p2 = 0;
        while(p1 < s.length() && p2 < p.length()) {
            if(s.charAt(p1) == p.charAt(p2) && !excludedIndexes.contains(p1))
                p2++;
            p1++;
        }

        return p2 == p.length();
    }
}
