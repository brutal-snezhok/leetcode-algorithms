package dp.medium;

import java.util.Arrays;

// https://leetcode.com/problems/jump-game/description/
public class JumpGame_55 {
    // solution1
    public boolean canJump1(int[] nums) {
        // recursive backtracking,TLE
        // time O(n^n)
        // space O(n)

        return jump1(nums, 0);
    }

    private boolean jump1(int[] nums, int ind) {
        if(ind == nums.length - 1)
            return true;
        if(nums[ind] == 0)
            return false;

        // jump can be in range [ind + 1, ind + nums[ind]]
        for(int i = ind + 1; i <= ind + nums[ind]; i++)
            if(i < nums.length && jump1(nums, i))
                return true;

        return false;
    }

    // solution2
    public boolean canJump2(int[] nums) {
        // dp with memo
        // time O(n^2), for each index, I can have at max N jumps, hence O(N* N).
        // space O(n) + O(n), stack space plus dp array size
        Boolean[] dp = new Boolean[nums.length];

        return jump2(nums, 0, dp);
    }

    private boolean jump2(int[] nums, int ind, Boolean[] dp) {
        if(ind == nums.length - 1)
            return true;
        if(nums[ind] == 0)
            return false;

        if(dp[ind] != null) return dp[ind];
        // jump can be in range [ind + 1, ind + nums[ind]]
        for(int i = ind + 1; i <= ind + nums[ind]; i++)
            if(i < nums.length && jump2(nums, i, dp))
                return dp[ind] = true;

        return dp[ind] = false;
    }

    // solution3
    public boolean canJump3(int[] nums) {
        // dp with memo, iterative
        // time O(n^2), for each index, I can have at max N jumps, hence O(N*N).
        // space O(n), dp array size

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[n - 1] = 1;

        for(int ind = n - 2; ind >= 0; ind--) {
            if(nums[ind] == 0) {
                dp[ind] = 0;
                continue;
            }

            int flag = 0;
            int reach = ind + nums[ind];
            for(int jump = ind + 1; jump <= reach; jump++)
                if(jump < nums.length && dp[jump] == 1) {
                    dp[ind] = 1;
                    flag = 1;
                    break;
                }

            if(flag == 1)
                continue;

            dp[ind] = 0;
        }

        return dp[0] == 1;
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
