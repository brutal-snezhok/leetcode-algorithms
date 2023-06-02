package twoPointers.medium;

import java.util.Arrays;

// https://leetcode.com/problems/3sum-closest/description/
public class TreeSumClosest_16 {
    public int threeSumClosest(int[] nums, int target) {
        // time O(n^2)
        // space O(n), for sorting

        // nums[k] + nums[i] + nums[j] - target -> 0

        Arrays.sort(nums);
        int n = nums.length;
        int sum = Integer.MAX_VALUE;

        for(int k = 0; k < n; k++) {
            int i = k + 1;
            int j = n - 1;
            while(i < j) {
                int curr = nums[k] + nums[i] + nums[j];

                if(Math.abs(target - curr) < Math.abs(target - sum))
                    sum = curr;
                else if(target > curr)
                    i++;
                else
                    j--;
            }
        }

        return sum;
    }
}
