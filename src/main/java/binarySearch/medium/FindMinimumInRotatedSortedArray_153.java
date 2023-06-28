package binarySearch.medium;

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
public class FindMinimumInRotatedSortedArray_153 {
    // solution1
    public int findMin1(int[] nums) {
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

    // solution2
    public int findMin2(int[] nums) {
        // time O(logn)
        // space O(1)

        /*   0 1 2 3 4
            [3,4,5,1,2]
             l   m   r

             if(nums[mid] < nums[r])
                r = mid;
             else
                l = mid + 1;
        */

        int l = 0;
        int r = nums.length - 1;

        while(l < r) {
            int mid = l + (r - l) / 2;

            if(nums[mid] < nums[r])
                r = mid;
            else
                l = mid + 1;
        }

        return nums[l];
    }
}
