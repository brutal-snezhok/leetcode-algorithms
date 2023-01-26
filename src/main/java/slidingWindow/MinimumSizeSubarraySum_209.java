package slidingWindow;

// https://leetcode.com/problems/minimum-size-subarray-sum/description/
public class MinimumSizeSubarraySum_209 {
    public int minSubArrayLen(int target, int[] nums) {
        // time O(n)
        // space O(1)

        // sliding window
        // go through array
        // if currSum >= target then res = min(res, r - l + 1), currSum -= nums[l], l++
        // if currSum < target, r++

        //  0 1 2 3 4 5
        // [2,3,1,2,4,3]
        // currSum = 6,7

        int l = 0;
        int res = Integer.MAX_VALUE;
        int currSum = 0;
        for(int r = 0; r < nums.length; r++) {
            currSum += nums[r];
            while(currSum >= target) {
                res = Math.min(res, r - l + 1);
                currSum -= nums[l];
                l++;
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
