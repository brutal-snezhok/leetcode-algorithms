package binarySearch.medium;

// https://leetcode.com/problems/search-in-rotated-sorted-array/
public class SearchInRotatedSortedArray_33 {
    public int search(int[] nums, int target) {
        // time O(logn)
        // space O(n)

        // determine in left or right part are you now
        // then check num[mid] ><= target and determine where move a pointer

        int l = 0;
        int r = nums.length - 1;
        int res = -1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(nums[mid] == target) {
                res = mid;
                break;
            }

            if(nums[mid] >= nums[l]) {
                // in left part
                if(target > nums[mid] || target < nums[l])
                    l = mid + 1;
                else
                    r = mid - 1;
            } else {
                // in right part
                if(target < nums[mid] || target > nums[r])
                    r = mid - 1;
                else
                    l = mid + 1;
            }
        }

        return res;
    }
}
