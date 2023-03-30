package dp.medium;

// https://leetcode.com/problems/house-robber-ii/
public class HouseRobberII_213 {
    public int rob(int[] nums) {
        // time O(n)
        // space O(1)

        int n = nums.length;
        if(n == 1)
            return nums[0];

        return Math.max(robHelper(nums, 0, n - 2), robHelper(nums, 1, n - 1));
    }

    private int robHelper(int[] nums, int start, int end) {
        int rob1 = 0;
        int rob2 = 0;
        for(int i = start; i <= end; i++) {
            int temp = Math.max(rob2, rob1 + nums[i]);
            rob1 = rob2;
            rob2 = temp;
        }

        return rob2;
    }
}
