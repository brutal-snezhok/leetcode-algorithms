package binarySearch.medium;

// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
public class FindFirstAndLastPositionOfElementInSortedArray_34 {
    public int[] searchRange(int[] nums, int target) {
        // time O(logn)
        // space O(1)

        // run binary search twon times. first - find first position, second - last position
        // modify bs

        int[] res = new int[2];
        res[0] = binarySearch(nums, target, true);
        res[1] = binarySearch(nums, target, false);

        return res;
    }

    private int binarySearch(int[] nums, int target, boolean isLeft) {
        int l = 0;
        int r = nums.length - 1;
        int ind = -1;

        while(l <= r) {
            int mid = l + (r - l) / 2;

            if(nums[mid] > target)
                r = mid - 1;
            else if(nums[mid] < target)
                l = mid + 1;
            else {
                ind  = mid;
                if(isLeft)
                    r = mid - 1;
                else
                    l = mid + 1;
            }
        }

        return ind;
    }
}
