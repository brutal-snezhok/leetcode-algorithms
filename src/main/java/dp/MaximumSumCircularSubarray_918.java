package dp;

// https://leetcode.com/problems/maximum-sum-circular-subarray/
public class MaximumSumCircularSubarray_918 {
    // Calculate the "Minimum Subarray"
    public int maxSubarraySumCircular(int[] nums) {
        // Kadan's algo
        // time O(n)
        // space O(1)

        int currMin = 0;
        int globalMin = nums[0];

        int currMax = 0;
        int globalMax = nums[0];
        int sum = 0;

        for (int num : nums) {
            currMin = Math.min(num, currMin + num);
            globalMin = Math.min(globalMin, currMin);

            currMax = Math.max(num, currMax + num);
            globalMax = Math.max(globalMax, currMax);

            sum += num;
        }

        return sum == globalMin ? globalMax : Math.max(globalMax, sum - globalMin);
    }
}
