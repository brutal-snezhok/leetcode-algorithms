package binarySearch.hard;

// https://leetcode.com/problems/split-array-largest-sum/description/
public class SplitArrayLargestSum_410 {
    public int splitArray(int[] nums, int k) {
        // time O(nlogs)
        // space O(1)

        // use binary search
        // l = max(nums), r = sum(nums)

        int l = Integer.MIN_VALUE;
        int r = 0;
        for (int num : nums) {
            l = Math.max(l, num);
            r += num;
        }

        int res = Integer.MAX_VALUE;
        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (valid(mid, nums, k)) {
                res = Math.min(res, mid);
                r = mid - 1;
            } else
                l = mid + 1;

        }

        return res;
    }

    private boolean valid(int mid, int[] nums, int k) {
        // check if mid is satisfy the condition
        int count = 1;
        int currSum = 0;
        for (int num : nums) {
            currSum += num;
            if (currSum > mid) {
                count++;
                currSum = num;

                if (count > k)
                    return false;
            }
        }

        return true;
    }
}
