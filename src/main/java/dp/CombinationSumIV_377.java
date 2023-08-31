package dp;

import java.util.Arrays;

// https://leetcode.com/problems/combination-sum-iv/
public class CombinationSumIV_377 {
    // solution1
    public int combinationSum4_1(int[] nums, int target) {
        // brute-force, TLE
        // time O(n^t)
        // space O(t)

        Arrays.sort(nums);
        return backtracking1(nums, target, 0);
    }

    private int backtracking1(int[] nums, int target, int sum) {
        if(sum == target)
            return 1;

        int res = 0;
        for(int num : nums) {
            if(num + sum > target)
                break;

            res += backtracking1(nums, target, sum + num);
        }

        return res;
    }

    // solution2
    public int combinationSum4_2(int[] nums, int target) {
        // top-down dp
        // time O(n * t), n - number of elements in nums, t - target val
        // space O(t)

        Arrays.sort(nums);
        int[] memo = new int[target];
        Arrays.fill(memo, -1);

        return backtracking2(nums, target, 0, memo);
    }

    private int backtracking2(int[] nums, int target, int sum, int[] memo) {
        if(sum == target)
            return 1;
        if(memo[sum] != -1)
            return memo[sum];

        int res = 0;
        for(int num : nums) {
            if(num + sum > target)
                break;

            res += backtracking2(nums, target, sum + num, memo);
        }

        return memo[sum] = res;
    }

    // solution3
    public int combinationSum4_3(int[] nums, int target) {
        // bottom-up, dp
        // time O(n * t), n - number of elements in nums, t - target val
        // space O(t)

        Arrays.sort(nums);
        int[] dp = new int[target + 1]; // number of possible combinations
        dp[0] = 1;

        for(int currTarget = 1; currTarget <= target; currTarget++) {
            for(int num : nums) {
                if(num > currTarget)
                    break;

                dp[currTarget] += dp[currTarget - num];
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        CombinationSumIV_377 combination = new CombinationSumIV_377();
        combination.combinationSum4_2(new int[]{1, 2, 3}, 4);
    }
}
