package dp.medium.zero_one_knapsack;

import java.util.Arrays;

// https://leetcode.com/problems/target-sum/description/
public class TargetSum_494 {
    // solution1
    public int findTargetSumWays1(int[] nums, int target) {
        // time O(2^n)
        // space O(n)

        // on each index we have two choise:
        // 1. take +val
        // 2. take -val

        return dfs1(nums, target, 0, 0);
    }

    private int dfs1(int[] nums, int target, int i, int currSum) {
        if(i == nums.length && currSum == target)
            return 1;
        if(i == nums.length && currSum != target)
            return 0;

        int plusVal = dfs1(nums, target, i + 1, currSum + nums[i]);
        int minusVal = dfs1(nums, target, i + 1, currSum - nums[i]);

        return plusVal + minusVal;
    }

    // solution2
    int total;

    public int findTargetSumWays2(int[] nums, int target) {
        // time O(s*n), s - sum of all vals, n - number of elements
        // space O(s*n)

        // on each index we have two chooses:
        // 1. take +val
        // 2. take -val
        // use memo

        total = Arrays.stream(nums).sum();
        int[][] memo = new int[nums.length][2 * total + 1];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }

        return dfs2(nums, target, 0, 0, memo);
    }

    private int dfs2(int[] nums, int target, int i, int currSum, int[][] memo) {
        if(i == nums.length && currSum == target)
            return 1;
        if(i == nums.length && currSum != target)
            return 0;
        if(memo[i][currSum + total] != Integer.MIN_VALUE)
            return memo[i][currSum + total];

        int plusVal = dfs2(nums, target, i + 1, currSum + nums[i], memo);
        int minusVal = dfs2(nums, target, i + 1, currSum - nums[i], memo);
        memo[i][currSum + total] = plusVal + minusVal;

        return memo[i][currSum + total];
    }
}
