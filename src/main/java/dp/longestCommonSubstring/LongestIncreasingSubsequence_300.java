package dp.longestCommonSubstring;

import java.util.Arrays;

// https://leetcode.com/problems/longest-increasing-subsequence/description/
public class LongestIncreasingSubsequence_300 {
    // solution1
    public int lengthOfLIS1(int[] nums) {
        // brute force, TLE
        // time O(2^n)
        // space O(n)

        return dfs(nums, 0, Integer.MIN_VALUE);
    }

    private int dfs(int[] nums, int ind, int prev) {
        if (ind == nums.length)
            return 0;

        int take = 0;
        if (nums[ind] > prev)
            take = 1 + dfs(nums, ind + 1, nums[ind]);
        int notTake = dfs(nums, ind + 1, prev);

        return Math.max(take, notTake);
    }

    // solution2
    public int lengthOfLIS2(int[] nums) {
        // top down, memo
        // time O(n^2)
        // space O(n^2)

        int n = nums.length;
        Integer[][] memo = new Integer[n][n + 1];

        return dfs(nums, 0, -1, memo);
    }

    private int dfs(int[] nums, int ind, int prev_ind, Integer[][] memo) {
        if(ind == nums.length)
            return 0;
        if(memo[ind][prev_ind + 1] != null)
            return memo[ind][prev_ind + 1];

        int take = 0;
        if(prev_ind == -1 || nums[ind] > nums[prev_ind])
            take = 1 + dfs(nums, ind + 1, ind, memo);
        int notTake = dfs(nums, ind + 1, prev_ind, memo);

        return memo[ind][prev_ind + 1] = Math.max(take, notTake);
    }

    // solution3
    public int lengthOfLIS3(int[] nums) {
        // bottom up, dp
        // time O(n^2)
        // space O(n)

        // dp[i] - lis ending at index i

        int n = nums.length;
        int res = 1;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    res = Math.max(res, dp[i]);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence_300 increasingSubsequence_300 = new LongestIncreasingSubsequence_300();
        increasingSubsequence_300.lengthOfLIS3(new int[]{10,9,2,5,3,7,101,18});
    }
}
