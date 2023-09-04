package dp.zeroOneKnapsack;

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

    // solution2_1
    public boolean canPartition2_1(int[] nums) {
        // time O(n * sum)
        // space O(n * sum)

        /*
            1. count sum of array
            2. if sum % 2 != 0 -> return false
            3. sumOfSubset = sum / 2;
            4. go through arr and we have two choices whether we can take or not the element
        */

        int sum = 0;
        for(int num : nums)
            sum += num;

        if(sum % 2 != 0)
            return false;

        Boolean[][] memo = new Boolean[nums.length][sum / 2 + 1];

        return backtracking(nums, 0, sum / 2, 0, memo);
    }

    private boolean backtracking(int[] nums, int ind, int subsetSum, int currSum, Boolean[][] memo) {
        if(currSum == subsetSum)
            return true;
        if(ind == nums.length)
            return false;
        if(memo[ind][currSum] != null)
            return memo[ind][currSum];

        boolean take = false;
        if(currSum + nums[ind] <= subsetSum)
            take = backtracking(nums, ind + 1, subsetSum, currSum + nums[ind], memo);

        boolean notTake = backtracking(nums, ind + 1, subsetSum, currSum, memo);

        return memo[ind][currSum] = take || notTake;
    }

    // solution3
    int total1;
    public boolean canPartition3(int[] nums) {
        // bottom up
        // time O(s*n)
        // space O(s*n)

        total1 = Arrays.stream(nums).sum();
        if(total1 % 2 != 0)
            return false;

        int n = nums.length;
        boolean[][] dp = new boolean[nums.length][total1 / 2 + 1];
        for(int i = 0; i < n; i++)
            dp[i][0] = true;

        for(int s=1; s < total1 / 2 + 1 ; s++)
            dp[0][s] = (nums[0] == s ? true : false);

        for(int i = 1; i < n; i++) {
            for(int s = 1; s < total1 / 2 + 1; s++) {
                dp[i][s] = dp[i - 1][s] || (s >= nums[i] ? dp[i - 1][s - nums[i]] : false) ;
            }
        }

        return dp[n - 1][total1 / 2];
    }

    // solution4
    public boolean canPartition4(int[] nums) {
        // description: https://takeuforward.org/data-structure/partition-equal-subset-sum-dp-15/
        // time O(n * k) + O(n)
        // space O(n * k)

        int total = 0;
        for(int num : nums)
            total += num;

        if(total % 2 != 0)
            return false;

        int k = total / 2;
        boolean[][] dp = new boolean[nums.length][k + 1];
        for(int r = 0; r < nums.length; r++)
            dp[r][0] = true;

        for(int ind = 1; ind < nums.length; ind++) {
            for(int s = 1; s < k + 1; s++) {
                boolean notTaken = dp[ind - 1][s];
                boolean taken = (s >= nums[ind]) ? dp[ind - 1][s - nums[ind]] : false;
                dp[ind][s] = taken || notTaken;
            }
        }

        return dp[nums.length - 1][k];
    }
}
