package arraysHashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/contains-duplicate/description/
public class ContainsDuplicate_217 {
    public boolean containsDuplicate(int[] nums) {
        // time O(n)
        // space O(n)

        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            if(set.contains(num))
                return true;
            else
                set.add(num);
        }

        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        // time O(n^2)
        // space O(1)

        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsDuplicate3(int[] nums) {
        // time O(nlogn)
        // space O(logn) - Arrays.sort() - variation of quick sort that takes for O(logn) space

        Arrays.sort(nums);
        for(int ind = 1; ind < nums.length; ind++) {
            if(nums[ind] == nums[ind - 1]) {
                return true;
            }
        }
        return false;
    }
}
