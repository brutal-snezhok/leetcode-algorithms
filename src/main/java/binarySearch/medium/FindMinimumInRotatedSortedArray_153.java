package binarySearch.medium;

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
public class FindMinimumInRotatedSortedArray_153 {
    public int findMin(int[] nums) {
        // time O(logn)
        // space O(1)

        // base case: if nums[l] <= nums[r] -> means that it is sorted arr and res = Math.min(res, nums[l]);
        // find mid: if nums[mid] >= nums[l] -> l = mid + 1 (try to find el in right part)
        // else try to find min element in left part

        int l = 0;
        int r = nums.length - 1;
        int res = Integer.MAX_VALUE;
        while(l <= r) {
            if(nums[l] <= nums[r]) {
                res = Math.min(res, nums[l]); // for case: [3,1,2]
                break;
            }

            int mid = (l + r) / 2;
            res = Math.min(res, nums[mid]);
            if(nums[mid] >= nums[l])
                l = mid + 1;
            else
                r = mid - 1;
        }

        return res;
    }
}
