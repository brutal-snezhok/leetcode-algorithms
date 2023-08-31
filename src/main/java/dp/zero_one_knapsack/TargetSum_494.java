package dp.zero_one_knapsack;

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
    // comment which describes why should use 2 * total: https://leetcode.com/problems/target-sum/editorial/comments/1263710

    int total;
    public int findTargetSumWays(int[] nums, int target) {
        // time O(n * s)
        // space O(n * s)

        total = Arrays.stream(nums).sum();
        int n = nums.length;
        Integer[][] memo = new Integer[n][2 * total + 1];

        return dfs(nums, target, 0, 0, memo);
    }

    private int dfs(int[] nums, int target, int i, int currSum, Integer[][] memo) {
        if(i == nums.length && currSum == target)
            return 1;
        if(i == nums.length && currSum != target)
            return 0;
        if(memo[i][currSum + total] != null)
            return memo[i][currSum + total];

        int positive = dfs(nums, target, i + 1, currSum + nums[i], memo);
        int negative = dfs(nums, target, i + 1, currSum - nums[i], memo);
        memo[i][currSum + total] = positive + negative;

        return memo[i][currSum + total];
    }
}
