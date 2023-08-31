package dp;

// https://leetcode.com/problems/maximum-product-subarray/
public class MaximumProductSubarray_152 {
    public int maxProduct1(int[] nums) {
        // brute force
        // time O(n)
        // space O(1)

        int n = nums.length;
        int res = nums[0];
        int currMax;

        for (int i = 0; i < n; i++) {
            currMax = 1;
            for (int j = i; j < n; j++) {
                currMax *= nums[j];
                res = Math.max(res, currMax);
            }
        }

        return res;
    }

    public static int maxProduct(int[] nums) {
        // Kadan's algo
        // time O(n)
        // space O(1)

        // since we have negative nums
        // we should do two Kadan's algos from left to right and vise versa
        // see example arr {-8,5,3,1,6}
        // from one pass res will be -720, which is incorrect

        int n = nums.length;
        int res = nums[0];
        int currMax = 1;

        // from left to right
        for (int num : nums) {
            currMax = Math.max(num, currMax * num);
            res = Math.max(res, currMax);
        }

        currMax = 1;
        // from right to left
        for (int i = n - 1; i >= 0; i--) {
            currMax = Math.max(nums[i], currMax * nums[i]);
            res = Math.max(res, currMax);
        }

        return res;
    }

    public static void main(String[] args) {
        maxProduct(new int[]{-2, 3, -4});
    }
}
