package dp.medium.zero_one_knapsack;

import java.util.Arrays;

// https://leetcode.com/problems/partition-equal-subset-sum/description/
public class PartitionEqualSubsetSum_416 {
    // solution1, TLE
    public boolean canPartition1(int[] nums) {
        // time O(2^n)
        // space O(n)

        int total = Arrays.stream(nums).sum();
        if(total % 2 != 0)
            return false;

        return dfs1(nums, total / 2, 0, 0);
    }

    private boolean dfs1(int[] nums, int target, int i, int sum) {
        if(i == nums.length && target == sum)
            return true;
        if(i == nums.length && target != sum)
            return false;

        boolean takeVal = dfs1(nums, target, i + 1, sum + nums[i]);
        boolean doNotTakeVal = dfs1(nums, target, i + 1, sum);

        return takeVal || doNotTakeVal;
    }

    // solution2
    int total;
    public boolean canPartition2(int[] nums) {
        // time O(s*n)
        // space O(s*n)

        total = Arrays.stream(nums).sum();
        if(total % 2 != 0)
            return false;

        Boolean[][] memo = new Boolean[nums.length][total + 1];

        return dfs2(nums, total / 2, 0, 0, memo);
    }

    private boolean dfs2(int[] nums, int target, int i, int sum, Boolean[][] memo) {
        if(i == nums.length && target == sum)
            return true;
        if(i == nums.length && target != sum)
            return false;
        if(memo[i][sum] != null)
            return memo[i][sum];

        boolean takeVal = dfs2(nums, target, i + 1, sum + nums[i], memo);
        boolean doNotTakeVal = dfs2(nums, target, i + 1, sum, memo);

        memo[i][sum] = takeVal || doNotTakeVal;
        return memo[i][sum];
    }
}
