package backtracking.easy;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/sum-of-all-subset-xor-totals/
public class SumOfAllSubsetXORTotals_1863 {
    public int subsetXORSum(int[] nums) {
        // time O(n * 2^n)
        // space O(n * 2^n)

        // find all subsets with backtracking
        // do sum of XOR in each list

        List<List<Integer>> res = new ArrayList<>();
        backtracking(nums, res, new ArrayList<>(), 0);

        int sum = 0;
        for(List<Integer> list : res) {
            int xor = 0;
            for(int num : list)
                xor = xor ^ num;

            sum += xor;
        }

        return sum;
    }

    private void backtracking(int[] nums, List<List<Integer>> res, List<Integer> curr, int ind) {
        res.add(new ArrayList<>(curr));
        for(int i = ind; i < nums.length; i++) {
            curr.add(nums[i]);
            backtracking(nums, res, curr, i + 1);
            curr.remove(curr.size() - 1);
        }
    }
}
