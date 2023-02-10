package backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/subsets-ii/description/
public class SubsetsII_90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // time O(2^n*n)
        // space O(n), didn't count res list

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(nums, res, new ArrayList<>(), 0);

        return res;
    }

    private void backtracking(int[] nums, List<List<Integer>> res, List<Integer> curr, int start) {
        res.add(new ArrayList<>(curr));
        for(int i = start; i < nums.length; i++) {
            if(i > start && nums[i] == nums[i - 1]) continue; // skip duplicates

            curr.add(nums[i]);
            backtracking(nums, res, curr, i + 1);
            curr.remove(curr.size() - 1);
        }
    }
}
