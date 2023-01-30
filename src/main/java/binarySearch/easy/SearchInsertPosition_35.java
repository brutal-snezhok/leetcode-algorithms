package binarySearch.easy;

// https://leetcode.com/problems/search-insert-position/description/
public class SearchInsertPosition_35 {
    public int searchInsert(int[] nums, int target) {
        // time O(logn)
        // space O(1)

        int l = 0;
        int r = nums.length - 1;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if(nums[mid] == target)
                return mid;
            else if(nums[mid] > target)
                r = mid - 1;
            else
                l = mid + 1;
        }

        return l;
    }
}
