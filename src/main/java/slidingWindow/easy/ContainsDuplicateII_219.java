package slidingWindow.easy;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/contains-duplicate-ii/
public class ContainsDuplicateII_219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // sliding window
        // time O(n)
        // space O(k)

        if(k == 0)
            return false;

        int l = 0;
        Set<Integer> slidingWindow = new HashSet<>();
        // [1,0,1,1]  k = 1
        for(int r = 0; r < nums.length; r++) {
            if(r - l > k) {
                slidingWindow.remove(nums[l]);
                l++;
            }

            if(slidingWindow.contains(nums[r]))
                return true;
            slidingWindow.add(nums[r]);
        }

        return false;
    }
}
