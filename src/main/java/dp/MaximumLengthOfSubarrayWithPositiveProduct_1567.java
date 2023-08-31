package dp;

// https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product/
public class MaximumLengthOfSubarrayWithPositiveProduct_1567 {
    public int getMaxLen(int[] nums) {
        // time O(n)
        // space O(1)

        // keep track on count of neg and pos length

        int res = 0;
        int pos = 0;
        int neg = 0;

        for(int num : nums) {
            if(num == 0) {
                pos = 0;
                neg = 0;
            } else if(num > 0) {
                pos++;
                neg = neg == 0 ? 0 : neg + 1;
            } else {
                int tmp = pos;
                pos = neg == 0 ? 0 : neg + 1;
                neg = tmp + 1;
            }

            res = Math.max(res, pos);
        }

        return res;
    }
}
