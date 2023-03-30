package dp.medium;

// https://leetcode.com/problems/house-robber/description/
public class HouseRobber_198 {
    public int rob1(int[] nums) {
        // time O(n)
        // space O(1)

        if (nums.length == 1)
            return nums[0];

        int rob1 = nums[0];
        int rob2 = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            int temp = Math.max(rob2, rob1 + nums[i]);
            rob1 = rob2;
            rob2 = temp;
        }

        return rob2;
    }

    public int rob2(int[] nums) {
        // time O(n)
        // space O(1)

        if(nums.length == 1)
            return nums[0];

        int rob1 = 0;
        int rob2 = 0;

        for(int i = 0; i < nums.length; i++) {
            int temp = Math.max(rob2, rob1 + nums[i]);
            rob1 = rob2;
            rob2 = temp;
        }

        return rob2;
    }

    public int rob3(int[] nums) {
        // time O(n)
        // space O(n)

        if (nums.length == 1)
            return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++)
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);

        return dp[dp.length - 1];
    }
}
