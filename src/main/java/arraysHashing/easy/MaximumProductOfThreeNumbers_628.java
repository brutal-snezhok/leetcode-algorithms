package arraysHashing.easy;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-product-of-three-numbers/description/
public class MaximumProductOfThreeNumbers_628 {
    public int maximumProduct1(int[] nums) {
        /*
         time O(nlogn)
         space O(logn), because of sort
         three are 3 cases:
         all nums are positive -> take the biggest three val
         all negative -> take smallest 3 val
         mixed -> compare max if take 2 neg, 1 pos, all 3 pos
        */

        int n = nums.length;
        if(n == 3)
            return nums[0] * nums[1] * nums[2];

        Arrays.sort(nums);
        // all pos or all neg
        if((nums[0] >= 0 && nums[n - 1] >= 0) || (nums[0] < 0 && nums[n - 1] < 0))
            return nums[n - 1] * nums[n - 2] * nums[n - 3];

        // mixed
        int res = 1;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 1] * nums[n - 2] * nums[n - 3]);
    }

    public int maximumProduct(int[] nums) {
        // single scan
        // time O(n)
        // space O(1)

        // we need to find 3 max values and 2 smallest vals

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for(int num : nums) {
            if(num < min1) {
                min2 = min1;
                min1 = num;
            } else if(num < min2)
                min2 = num;

            if(num > max3) {
                max1 = max2;
                max2 = max3;
                max3 = num;
            } else if(num > max2) {
                max1 = max2;
                max2 = num;
            } else if(num > max1)
                max1 = num;
        }

        return Math.max(max1 * max2 * max3, min1 * min2 * max3);
    }
}
