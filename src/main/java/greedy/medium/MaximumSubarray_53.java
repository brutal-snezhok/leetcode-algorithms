package greedy.medium;

// https://leetcode.com/problems/maximum-subarray/
public class MaximumSubarray_53 {
    public int maxSubArray(int[] nums) {
        // time O(n)
        // space O(1)

        // currSum = -2, 1, -2, -5, 4, 3, 5, 6, 1
        // res = min, 1, 1, 1, 4, 4, 5, 6, 6
        int currSum = nums[0];
        int res = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > currSum && currSum < 0)
                currSum = nums[i];
            else
                currSum += nums[i];

            res = Math.max(res, currSum);
        }

        return res;
    }

    public int maxSubArray2(int[] nums) {
        // time O(n)
        // space O(1)

        int maxEndingHere = nums[0];
        int maxSoFar = nums[0];
        for(int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }

    public int maxSubArray3(int[] nums) {
        // time O(n)
        // space O(1)

        int sum = nums[0];
        int max = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(sum < 0)
                sum = 0;

            sum += nums[i];
            max = Math.max(max, sum);
        }

        return max;
    }
}
