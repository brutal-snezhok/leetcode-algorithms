package dp.medium.fibonacci_numbers;

import java.util.Arrays;

// https://leetcode.com/problems/jump-game/description/
public class JumpGame_55 {
    // solution1
    public boolean canJump1(int[] nums) {
        // recursive backtracking,TLE
        // time O(n^n)
        // space O(n)

        // for each element we want to check all ids that can be reached
        // in range [ind + 1, ind + nums[ind]]

        return jump1(nums, 0);
    }

    private boolean jump1(int[] nums, int ind) {
        int n = nums.length;
        if(ind == n - 1)
            return true;
        if(nums[ind] == 0)
            return false;

        // jump can be in range [ind + 1, ind + nums[ind]]
        for(int i = ind + 1; i <= Math.min(ind + nums[ind], n - 1); i++)
            if(jump1(nums, i))
                return true;

        return false;
    }

    // solution2
    public boolean canJump2(int[] nums) {
        // top down, memo
        // time O(n^2), for each index, I can have at max N jumps, hence O(N* N).
        // space O(n) + O(n), stack space plus dp array size
        Boolean[] memo = new Boolean[nums.length];

        return jump2(nums, 0, memo);
    }

    private boolean jump2(int[] nums, int ind, Boolean[] memo) {
        int n = nums.length;
        if(ind == n - 1)
            return true;
        if(nums[ind] == 0)
            return false;
        if(memo[ind] != null)
            return memo[ind];

        // jump can be in range [ind + 1, ind + nums[ind]]
        for(int i = ind + 1; i <= Math.min(ind + nums[ind], n - 1); i++)
            if(jump2(nums, i, memo))
                return memo[ind] = true;

        return memo[ind] = false;
    }

    // solution3
    public boolean canJump3(int[] nums) {
        // top down, memo
        // time O(n^2), at each index we can have at most n jumps
        // space O(n)

        // dp[i] - flag if we can reach last position

        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[n - 1] = true;

        for(int i = n - 2; i >= 0; i--) {
            int start = i + 1;
            int end = Math.min(i + nums[i], n - 1);

            for(int j = start; j <= end; j++) {
                if(dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[0];
    }

    // solution4
    public boolean canJump4(int[] nums) {
        // dp with variable
        // time O(n)
        // space O(1)

        int reach = 0;
        for(int ind = 0; ind < nums.length; ind++) {
            if(reach < ind)
                return false;

            reach = Math.max(reach, ind + nums[ind]);
        }

        return true;
    }

    // solution5
    public boolean canJump5(int[] nums) {
        // dp with variable
        // time O(n)
        // space O(1)

        int n = nums.length;
        int goal = n - 1;
        for(int ind = n - 1; ind >= 0; ind--) {
            if(ind + nums[ind] >= goal)
                goal = ind;
        }

        return goal == 0;
    }
}
